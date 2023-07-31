package com.occidente.occidente.services;

import com.occidente.occidente.Entities.Groups;
import com.occidente.occidente.Entities.Sizes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusService {
    public Sizes calculoCapacidad(Groups grupos) {
        String[] entrada = grupos.getGroups().split(",");
        List<Integer> list = new ArrayList<>();
        for(String s:entrada){
            list.add(Integer.parseInt(s));
        }
        String resul = "";
        int acum = 0;
        for (int i = 0; i < list.size(); i++) {
            acum += list.get(i);
        }
        for (int x = 1; x <= acum; x++) {// para buscar los tamanios de bus
            boolean busApto = busAceptable(x, list);
            if (busApto)
                resul+=x+",";
        }
        System.out.println(resul);
        return new Sizes(resul);
    }

    /*** Logica: Si en algun momento se supera la capacidad del bus
     ** descarto y finalizo cilo, si no se supera la capacidad
     ** al final reviso que no sobren puestos.* */
    private static boolean busAceptable(int tamanio, List<Integer> lista) {
        int acum = 0;
        boolean pasa = false;
        for (int num : lista) {
            acum += num;
            //supera la capacidad del bus-----------
            if (acum > tamanio) {
                pasa = false;
                break;
            }
            //se llena un bus y reseteo, envio otro igual--------
            if (acum == tamanio) {
                acum = 0;
            }
            //si termina el ciclo, significa que es apto el tamano
            pasa = true;
        }
        if (acum != 0) //apto en tamanio pero sobraron sillas
            pasa = false;
        return pasa;
    }
}