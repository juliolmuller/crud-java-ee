package br.com.beibe.beans;

import java.io.Serializable;
import java.util.List;

interface Bean extends Serializable {

    List<ValError> validate();
}
