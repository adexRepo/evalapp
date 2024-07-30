package com.kkp.evalapp.model;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private final List<String> columns = List.of("ID PEKERJA", "NAMA", "DIVISI", "DEPARTEMEN", "JABATAN", "LEVEL");

    private Integer idPekerja;
    private String namaPekerja;
    private String divisiPekerja;
    private String departemenPekerja;
    private String jabatanPekerja;
    private String levelPekerja;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee other = (Employee) obj;
        // Compare relevant properties here
        return Objects.equals(idPekerja, other.idPekerja);
    }

    @Override
    public int hashCode() {
        // Hash relevant properties here
        return Objects.hash(idPekerja);
    }

}
