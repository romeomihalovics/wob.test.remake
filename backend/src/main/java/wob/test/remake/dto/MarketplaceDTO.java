package wob.test.remake.dto;

import java.util.List;

public class MarketplaceDTO implements ValidSetterInterface, IdSetterInterface {
    private Integer id;
    private String marketplaceName;
    private boolean valid;
    private List<String> invalidFields;

    public MarketplaceDTO() {
        this.valid = true;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarketplaceName() {
        return marketplaceName;
    }

    public void setMarketplaceName(String marketplaceName) {
        this.marketplaceName = marketplaceName;
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
