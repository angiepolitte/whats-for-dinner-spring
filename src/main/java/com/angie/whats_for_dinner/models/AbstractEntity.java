//package com.angie.whats_for_dinner.models;
//
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.MappedSuperclass;
//
//import java.util.Objects;
//
//@MappedSuperclass
//public abstract class AbstractEntity {
//
//    @Id
//    @GeneratedValue
//    private int id;
//
//    public int getId() {
//        return id;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        AbstractEntity that = (AbstractEntity) o;
//        return id == that.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
//    }
//}