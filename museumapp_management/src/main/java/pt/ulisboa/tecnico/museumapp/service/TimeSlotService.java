package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.TimeSlotEntity;
import pt.ulisboa.tecnico.museumapp.models.Schedule;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface TimeSlotService {
    List<TimeSlotEntity> getAllTimeSlots();
    TimeSlotEntity createTimeSlot(TimeSlotEntity timeSlot);

    Optional<TimeSlotEntity> findTimeSlot(Integer timeSlotId);

    void deleteTimeSlot(Integer timeSlotId);

    List<Integer> getHours(String bHour, String eHour) throws ParseException;

    List<TimeSlotEntity> getTimeSlots(Schedule schedule) throws ParseException;

}
