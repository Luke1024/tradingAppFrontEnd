package frontend.client.dto;

import java.util.List;

public class DataPointDtoPack {
    private List<DataPointDto> dataPointDtoList;

    public DataPointDtoPack() {
    }

    public DataPointDtoPack(List<DataPointDto> dataPointDtoList) {
        this.dataPointDtoList = dataPointDtoList;
    }

    public List<DataPointDto> getDataPointDtoList() {
        return dataPointDtoList;
    }
}
