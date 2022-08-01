package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.ScheduleEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeSlotEntity;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<ScheduleEntity> getAllSchedules();
    ScheduleEntity createSchedule(ScheduleEntity schedule);

    Optional<ScheduleEntity> findSchedule(Integer scheduleId);

    List<TimeSlotEntity> getScheduleTimeSlots(Integer scheduleId);

    List<Integer> getHoursTimeSlots(List<TimeSlotEntity> timeSlotEntities);

    void deleteSchedule(Integer scheduleId);

}
