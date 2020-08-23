package wob.test.remake.dto;

import java.util.List;

public class ListingStatusDTO implements ValidSetterInterface, IdSetterInterface {
    private Integer id;
    private String statusName;
    private boolean valid;
    private List<String> invalidFields;

    public ListingStatusDTO() {
        this.valid = true;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(List<String> invalidFields) {
        this.invalidFields = invalidFields;
    }
}
