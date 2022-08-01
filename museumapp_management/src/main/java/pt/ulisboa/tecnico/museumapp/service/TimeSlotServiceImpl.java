package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp.entities.*;
import pt.ulisboa.tecnico.museumapp.models.Schedule;
import pt.ulisboa.tecnico.museumapp.repositories.TimeSlotRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {
    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    VisitService visitService;

    @Autowired
    VisitorService visitorService;

    @Override
    public List<TimeSlotEntity> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    @Override
    public TimeSlotEntity createTimeSlot(TimeSlotEntity timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }

    @Override
    public Optional<TimeSlotEntity> findTimeSlot(Integer timeSlotId) {
        return timeSlotRepository.findById(timeSlotId);
    }

    @Override
    public void deleteTimeSlot(Integer timeSlotId) {
        timeSlotRepository.deleteById(timeSlotId);
    }

    @Override
    public List<Integer> getHours(String bHour, String eHour) throws ParseException {
        List<Integer> hours = new ArrayList<>();
        String bDateTime = bHour;
        String eDateTime = eHour;
        String[] bHourH = bHour.split(":", 2);
        hours.add(parseInt(bHourH[0]));
        String[] eHourH = eHour.split(":", 2);
        hours.add(parseInt(eHourH[0]));
        return hours;
    }

    @Override
    public List<TimeSlotEntity> getTimeSlots(Schedule schedule, Integer scheduleId) throws ParseException {
        List<Integer> hours = getHours(schedule.getBeginningHour(), schedule.getEndingHour());
        int startTime = hours.get(0); //we have the "-1" in for because the first cycle would give from the real second
        int endTime = hours.get(1);
        List<Date> datesOfSchedule = schedule.getDatesOfSchedule();
        List<TimeSlotEntity> timeSlots = new ArrayList<>();
        for (Date d : datesOfSchedule) {
            for (int i = startTime - 1; i < endTime -1; i++) {
                int n = schedule.getTimeSlotsDuration();
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(d);
                calendar.set(Calendar.HOUR_OF_DAY, i);

                calendar.set(Calendar.MINUTE, 0);
                calendar.add(Calendar.MINUTE, n);

                calendar.set(Calendar.SECOND, 0);
                Date startDate = calendar.getTime();

                calendar.add(Calendar.HOUR, 0);
                calendar.add(Calendar.MINUTE, n);
                Date endDate = calendar.getTime();

                TimeSlotEntity timeSlot = new TimeSlotEntity(scheduleId, startDate, endDate, d, schedule.getCapacity());
                timeSlotRepository.save(timeSlot);
                timeSlots.add(timeSlot);

                n += schedule.getTimeSlotsDuration();
            }
        }
        return timeSlots;
    }
    @Override
    public TimeSlotEntity findTimeSlotByName(String name){
        for (TimeSlotEntity ts: timeSlotRepository.findAll()){
            if (ts.getName() == name){
                return ts;
            }
        }
        return null;
    }
    @Override
    public List<TimeSlotEntity> findTimeSlotBySchedule(Integer scheduleId){
        List<TimeSlotEntity> timeSlots = new ArrayList<>();
        for (TimeSlotEntity ts: timeSlotRepository.findAll()){
            if (ts.getScheduleId() == scheduleId){
                timeSlots.add(ts);
            }
        }
        return timeSlots;
    }
    @Override
    public List<TimeSlotEntity> findTimeSlotByVisit(Integer visit_id){
        VisitEntity visit = visitService.findVisit(visit_id).get();
        VisitorEntity visitor = visitorService.findVisitor(visit.getVisitor().getId()).get();
        String patternDate = "yyyy-MM-dd";
        DateFormat dfDate = new SimpleDateFormat(patternDate);
        List<TimeSlotEntity> timeSlots = new ArrayList<>();
        for (TimeSlotEntity ts : getAllTimeSlots()){
            String tsDateAsString = dfDate.format(ts.getDate());
            if (ts.getState() == TimeSlotState.AVAILABLE && tsDateAsString.equals(visit.getVisitDate()) && visitor.getNoVisitors() <= ts.getCapacity()){
                timeSlots.add(ts);
            }
        }
        return timeSlots;
    }


    @Override
    public Integer updateTimeSlot(Integer timeSlotId, Integer visitorId){
        TimeSlotEntity ts = findTimeSlot(timeSlotId).get();
        VisitorEntity v = visitorService.findVisitor(visitorId).get();
        Date startTime = ts.getStartTime();
        Date endTime = ts.getEndTime();
        Date date = ts.getDate();
        TimeSlotState state = ts.getState();
        Integer scheduleId = ts.getScheduleId();
        if(ts.getCapacity() == v.getNoVisitors()){
            state = TimeSlotState.FULL;
        }
        Integer capacity = ts.getCapacity() - v.getNoVisitors();
        String name = ts.getName();
        deleteTimeSlot(timeSlotId);
        TimeSlotEntity timeSlot= new TimeSlotEntity(startTime, endTime, date, scheduleId, capacity, state, name);
        createTimeSlot(timeSlot);
        return timeSlot.getId();
    }
}