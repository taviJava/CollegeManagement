package controller;

import model.dto.PrezentModel;
import model.service.PrezentService;
import java.util.List;

public class PrezentServices {
    private PrezentService prezentService=new PrezentService();
    private PrezentModel prezentModelDef=new PrezentModel();

    public void add(PrezentModel prezentModel){
        prezentService.add(prezentModel);
    }
    public void remove(PrezentModel prezentModel){
        prezentService.delete(prezentModel);
    }

    public PrezentModel getPrezentModel(int id){
        PrezentModel prezentModel= prezentService.findById(prezentModelDef,id);
        return prezentModel;
    }

    public List<PrezentModel> getPrezentModelList(){
        List<PrezentModel> prezentModelList=prezentService.getAll(prezentModelDef);
        return prezentModelList;
    }
    public void update(PrezentModel prezentModel){
        prezentService.update(prezentModel);
    }
}
