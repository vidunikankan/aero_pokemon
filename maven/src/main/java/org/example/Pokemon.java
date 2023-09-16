package org.example;

public class Pokemon {
    private String name;
    private Long base_defense_stat;
    private Long bds_scrabble_product;

    public String getName(){
        return this.name;
    }

    public Long get_base_defense_stat(){
        return this.base_defense_stat;
    }
    public Long getBds_scrabble_product(){
        return this.bds_scrabble_product;
    }

    public void set_base_defense_stat(Long num){
        this.base_defense_stat = num;
    }

    public void setName(String poke){
        this.name = poke;
    }

    public void setBds_scrabble_product(Long num){
        this.bds_scrabble_product = num;
    }

}
