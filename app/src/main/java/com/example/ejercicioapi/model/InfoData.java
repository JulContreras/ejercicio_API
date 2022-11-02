package com.example.ejercicioapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoData {
    @SerializedName("data") public List<Data> data;
}
