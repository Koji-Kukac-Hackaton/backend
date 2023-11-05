package com.codebooq.service;

import com.codebooq.model.domain.DemandHistory;
import com.codebooq.model.domain.response.DemandHistoryResponse;
import com.codebooq.repository.DemandHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandHistoryService {

    @Autowired
    private DemandHistoryRepository demandHistoryRepository;

    public List<DemandHistoryResponse> getAllDemandHistory() {

        List<DemandHistory> demandHistories = demandHistoryRepository.findAll();

        return demandHistories.stream()
                .map(history -> {
                    DemandHistoryResponse response = new DemandHistoryResponse();
                    response.setId(history.getId());
                    response.setTimestamp(history.getTimestamp());
                    response.setOccupiedPercentage(history.getOccupiedPercentage());
                    response.setParkingSpotZone(history.getParkingSpotZone().toString());
                    return response;
                }).toList();
    }
}
