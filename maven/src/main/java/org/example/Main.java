package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        List<Pokemon> pokedex_1010 = new ArrayList<>();

        //Reading in Pokedex data, adding to list of Pokemon
        Object obj = new JSONParser().parse(new FileReader("/Users/vikankanamge/IdeaProjects/aerodesign_pokemon/maven/src/main/java/org/example/pokemon_full.json"));
        JSONArray jarray = (JSONArray) obj;
        for(Object o: jarray){
            if(o instanceof JSONObject){
                JSONObject temp = (JSONObject) o;
                String temp_name = (String) temp.get("name");
                Long temp_bds = (Long) ((JSONObject)temp.get("stats")).get("defense");
                Pokemon temp_poke = new Pokemon();
                temp_poke.setName(temp_name);
                temp_poke.set_base_defense_stat(temp_bds);
                pokedex_1010.add(temp_poke);
            }
        }

        //Calculating scores + setting max as we go
        Long max_score = Long.valueOf(0);
        Pokemon winner = new Pokemon();
        for(Pokemon p: pokedex_1010){

            Long temp_prod = calculate_bds_scrabble_product(p);
            p.setBds_scrabble_product(temp_prod);
            if(max_score < temp_prod){
                max_score = temp_prod;
                winner = p;
            }
            System.out.print(p.getName() + " " + p.getBds_scrabble_product() + "\n");

        }

        //Printing highest
        System.out.print(winner.getName() + " " + winner.getBds_scrabble_product());

    }

    //product calculator function
    public static Long calculate_bds_scrabble_product(Pokemon p){
        String poke_name = p.getName();
        Long poke_bds = p.get_base_defense_stat();
        Long scrabbleScore = Long.valueOf(0);

        for(int i = 0; i < poke_name.length(); i++){
            scrabbleScore += findScrabbleVal(poke_name.charAt(i));

        }
        return scrabbleScore*poke_bds;

    }

    //terrible scrabble score function
    public static Long findScrabbleVal(char c){
        Long value = Long.valueOf(0);
        Set<Character> one_point = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'L', 'N', 'S', 'T', 'R',
                'a', 'e', 'i', 'o', 'u', 'l', 'n', 's', 't', 'r'));
        Set<Character> two_point = new HashSet<>(Arrays.asList('D', 'G', 'd', 'g'));
        Set<Character> three_point = new HashSet<>(Arrays.asList('B', 'C', 'M', 'P', 'b', 'c', 'm', 'p'));
        Set<Character> four_point = new HashSet<>(Arrays.asList('F', 'H', 'V', 'W', 'Y'));
        Set<Character> five_point = new HashSet<>(Arrays.asList('K', 'k'));
        Set<Character> eight_point = new HashSet<>(Arrays.asList('J', 'X', 'j', 'x'));
        Set<Character> ten_point = new HashSet<>(Arrays.asList('Q', 'Z', 'q', 'z'));

        if(one_point.contains((Character) c)){
            value = Long.valueOf(1);
            return value;
        } else if(two_point.contains((Character) c)){
            value = Long.valueOf(2);
            return value;
        }else if(three_point.contains((Character) c)){
            value = Long.valueOf(3);
            return value;
        } else if(four_point.contains((Character) c)){
            value = Long.valueOf(4);
            return value;
        } else if(five_point.contains((Character) c)){
            value = Long.valueOf(5);
            return value;
        }else if(eight_point.contains((Character) c)){
            value = Long.valueOf(8);
            return value;
        }else if(ten_point.contains((Character) c)){
            value = Long.valueOf(10);
            return value;
        } else {
            return value;
        }
    }
}