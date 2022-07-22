package pt.ulisboa.tecnico.museumapp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pt.ulisboa.tecnico.museumapp.entities.ScheduleEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeSlotEntity;
import pt.ulisboa.tecnico.museumapp.models.Schedule;
import pt.ulisboa.tecnico.museumapp.service.ScheduleService;
import pt.ulisboa.tecnico.museumapp.service.TimeSlotService;

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
    @GetMapping("/new-schedule")
    public String createScheduleForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        return "new-schedule";
    }

    @GetMapping("/list-schedule")
    public ModelAndView findAllSchedules() {
        ModelAndView mav = new ModelAndView("list-schedule");
        List<Schedule> schedules = new ArrayList<>();
        for (ScheduleEntity s : scheduleService.getAllSchedules()){
            System.out.println("findAllSchedulles: schedule to observe:");
            System.out.println(s);
            for(TimeSlotEntity ts : timeSlotService.getAllTimeSlots()){
                System.out.println("findAllSchedulles: time slot to observe:");
                System.out.println(ts);
                if(s.getId() == ts.getSchedule().getId()){
                    List<Integer> times = scheduleService.getHoursTimeSlots(s.getId());
                    Schedule schedule = new Schedule(s.getBeginingDate().toString(),s.getEndingDate().toString(),times.get(0).toString(), times.get(-1).toString(),s.getCapacity(), s.getTimeSlotsDuration());
                    schedules.add(schedule);
                }
            }
            if(schedules.isEmpty()){
                Schedule schedule = new Schedule(s.getBeginingDate().toString(),s.getEndingDate().toString(),"no-time-slots", "no-time-slots", s.getCapacity(), s.getTimeSlotsDuration());
                schedules.add(schedule);
            }
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

    @PostMapping("/save-schedule")
    public String saveSchedule(@ModelAttribute Schedule schedule) throws ParseException {
        Date bDate=new SimpleDateFormat("yyyy-MM-dd").parse(schedule.getBeginningDate());
        Date eDate=new SimpleDateFormat("yyyy-MM-dd").parse(schedule.getEndingDate());
        List<TimeSlotEntity> timeSlots = timeSlotService.getTimeSlots(schedule);
        System.out.println(schedule);
        ScheduleEntity scheduleFinal = new ScheduleEntity(bDate,eDate, schedule.getCapacity(), schedule.getTimeSlotsDuration());
        scheduleService.createSchedule(scheduleFinal);
        List<TimeSlotEntity> timeSlotsFinal = new ArrayList<>();
        for (TimeSlotEntity ts : timeSlots){
            timeSlotsFinal.add(ts);
            ts.setSchedule(scheduleFinal);
        }
        System.out.println(timeSlotsFinal);
        //Aqui os timeSlots no repositório têm schedule, mas não no mysql
        return "saved-schedule";
    }

    @GetMapping("/delete-schedule/{id}")
    public String deleteSchedule(@PathVariable(value = "id", required = false) Integer scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return "redirect:/list-schedule";
    }
}
