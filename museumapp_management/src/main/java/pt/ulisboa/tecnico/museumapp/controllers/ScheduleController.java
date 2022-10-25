package pt.ulisboa.tecnico.museumapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.entities.ScheduleEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeSlotEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.models.Schedule;
import pt.ulisboa.tecnico.museumapp.models.TimeMachine;
import pt.ulisboa.tecnico.museumapp.models.TimeSlot;
import pt.ulisboa.tecnico.museumapp.models.Visit;
import pt.ulisboa.tecnico.museumapp.service.ScheduleService;
import pt.ulisboa.tecnico.museumapp.service.TimeSlotService;
import pt.ulisboa.tecnico.museumapp.service.VisitService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller // This means that this class is a Controller
public class ScheduleController implements WebMvcConfigurer {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    TimeSlotService timeSlotService;
    private List<TimeSlotEntity> timeSlots;
    @Autowired
    VisitService visitService;

    @GetMapping("/new-schedule")
    public String createScheduleForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        return "new-schedule";
    }
    @GetMapping("/error-delete-schedule") public String errorDeleteSchedule() {
        return "error-delete-schedule";
    }

    @GetMapping("/list-schedule")
    public ModelAndView findAllSchedules() {
        ModelAndView mav = new ModelAndView("list-schedule");
        List<Schedule> schedules = new ArrayList<>();
        for (ScheduleEntity s : scheduleService.getAllSchedules()){
            List<Integer> times = scheduleService.getHoursTimeSlots(scheduleService.getScheduleTimeSlots(s.getId()));
            String pattern = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(pattern);
            String beginDate = df.format(s.getBeginingDate());
            String endDate = df.format(s.getEndingDate());
            Schedule schedule = new Schedule(s.getId(), beginDate, endDate, times.get(0).toString(), times.get(times.size()-1).toString(),s.getCapacity(), s.getTimeSlotsDuration());
            schedules.add(schedule);
        }
        mav.addObject("schedules", schedules);
        return mav;
    }

    @GetMapping("/list-time-slots")
    public ModelAndView findAllTimeSlotsFromSchedule(@RequestParam Integer schedule_id) {
        ModelAndView mav = new ModelAndView("list-time-slots");
        mav.addObject("timeSlots", scheduleService.getScheduleTimeSlots(schedule_id));
        return mav;
    }

    @GetMapping("/time-slot-state")
    public ModelAndView addTSState(@RequestParam Integer timeslot_id) {
        ModelAndView mav = new ModelAndView("time-slot-state");
        TimeSlotEntity ts = timeSlotService.findTimeSlot(timeslot_id).get();
        TimeSlot timeSlot = new TimeSlot(ts.getId(), ts.getState());
        mav.addObject("timeSlot", timeSlot);
        return mav;
    }

    @PostMapping("/save-time-slot-state")
    public String saveTSState(@ModelAttribute TimeSlot timeSlot) {
        TimeSlotEntity ts = timeSlotService.findTimeSlot(timeSlot.getId()).get();
        TimeSlotEntity timeSlotEntity = new TimeSlotEntity(ts.getStartTime(), ts.getEndTime(), ts.getDate(), ts.getScheduleId(), ts.getCapacity(), timeSlot.getState());
        timeSlotService.deleteTimeSlot(ts.getId());
        timeSlotService.createTimeSlot(timeSlotEntity);
        return "redirect:/list-schedule";
    }
    @PostMapping("/save-schedule")
    public String saveSchedule(@ModelAttribute Schedule schedule) throws ParseException {
        Date bDate=new SimpleDateFormat("yyyy-MM-dd").parse(schedule.getBeginningDate());
        Date eDate=new SimpleDateFormat("yyyy-MM-dd").parse(schedule.getEndingDate());
        ScheduleEntity scheduleFinal = new ScheduleEntity(bDate,eDate, schedule.getCapacity(), schedule.getTimeSlotsDuration());
        scheduleService.createSchedule(scheduleFinal);
        List<TimeSlotEntity> timeSlots = timeSlotService.getTimeSlots(schedule, scheduleFinal.getId());
        List<TimeSlotEntity> timeSlotsFinal = new ArrayList<>();
        for (TimeSlotEntity ts : timeSlots){
            timeSlotsFinal.add(ts);
            ts.setScheduleId(scheduleFinal.getId());
        }

        return "saved-schedule";
    }

    @GetMapping("/delete-schedule/{id}")
    public String deleteSchedule(@PathVariable(value = "id", required = false) Integer scheduleId) {
        ScheduleEntity schedule = scheduleService.findSchedule(scheduleId).get();
        for (VisitEntity visit : visitService.getAllVisits()) {
            if(timeSlotService.checkTimeSlot(visit, schedule.getId())){
                System.out.println("true");
                return "redirect:/error-delete-schedule";
            }
        }
        scheduleService.deleteSchedule(scheduleId);
        return "redirect:/list-schedule";
    }
}
