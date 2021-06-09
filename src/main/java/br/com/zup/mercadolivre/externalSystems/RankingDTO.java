package br.com.zup.mercadolivre.externalSystems;

public class RankingDTO {

    private Long shoppingId;

    private Long ownerId;

    public RankingDTO(Long shoppingId, Long ownerId) {
        this.shoppingId = shoppingId;
        this.ownerId = ownerId;
    }

    public Long getShoppingId() {
        return shoppingId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public String toString() {
        return "RankingDTO{" +
                "shoppingId=" + shoppingId +
                ", ownerId=" + ownerId +
                '}';
    }
}
