package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ulisboa.tecnico.museumapp.entities.ScheduleEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeSlotEntity;
import pt.ulisboa.tecnico.museumapp.models.Schedule;
import pt.ulisboa.tecnico.museumapp.repositories.ScheduleRepository;
import pt.ulisboa.tecnico.museumapp.repositories.TimeSlotRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Override
    public List<ScheduleEntity> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleEntity createSchedule(ScheduleEntity schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Optional<ScheduleEntity> findSchedule(Integer scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }

    @Override
    public List<TimeSlotEntity> getScheduleTimeSlots(Integer scheduleId) {
        List<TimeSlotEntity> timeSlotEntities = new ArrayList<>();
        ScheduleEntity s = scheduleRepository.findById(scheduleId).get();
        for (TimeSlotEntity ts : timeSlotRepository.findAll()){
            if(s.getId() == ts.getScheduleId()){
                timeSlotEntities.add(ts);
            }
        }
        return timeSlotEntities;
    }


    @Override
    public List<Integer> getHoursTimeSlots(Integer scheduleId){
        List<TimeSlotEntity> timeSlotEntities = getScheduleTimeSlots(scheduleId);
        Date startDate = timeSlotEntities.get(0).getStartTime();
        List<Integer> timeSlotTimes = new ArrayList<>();
        int startTime = startDate.getHours();
        timeSlotTimes.add(startTime);
        Date endDate = timeSlotEntities.get(timeSlotEntities.size() - 1).getEndTime();
        int endTime= endDate.getHours();
        timeSlotTimes.add(endTime);
        return timeSlotTimes;
    }

    @Override
    public void deleteSchedule(Integer scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
