package application.ecoTracker.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.ecoTracker.DAO.CompaignDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.domain.Compaign;
import application.ecoTracker.domain.Observation;
import application.ecoTracker.service.DTO.CompaignDTO;
import application.ecoTracker.service.data.ObservationData;



@RestController
@RequestMapping("/compaign")
public class CompaignController {

    @Autowired
    private CompaignDAO compaignDAO;

    @Autowired
    private ObservationDAO observationDAO;

    @RequestMapping("/{id}")
    @ResponseBody
    public CompaignDTO findById(@PathVariable long id){
       Compaign compaign =  compaignDAO.findById(id).get();
       return new CompaignDTO(compaign);
    }

    
    @RequestMapping("/create")
    @ResponseBody
    public CompaignDTO create(@RequestBody CompaignDTO compaignDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Compaign compaign = new Compaign(compaignDTO.getName(), compaignDTO.getDescription(), LocalDateTime.parse(compaignDTO.getStartDate(), formatter), LocalDateTime.parse(compaignDTO.getEndDate(), formatter), compaignDTO.getGroupsToIdentify(), compaignDTO.getArea());
        compaignDAO.save(compaign);
        return compaignDTO;

    }

    @RequestMapping("/{id}/observations")
    @ResponseBody
    public List<ObservationData> findObservationsWithId(@PathVariable long id){
        List<Observation> observationList = observationDAO.findByCompaignId(id);

        List<ObservationData> observationDataList = new ArrayList<>();
        for(Observation observation : observationList){
            observationDataList.add(new ObservationData(observation));
        }

        return observationDataList;

    }


    
}
