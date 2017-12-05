package com.Jpa.Domain;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Currencies")
@NamedQueries({
        @NamedQuery(name = "Currency.findByCode", query = "SELECT c FROM Currency c WHERE c.code = :code")
})
public class Currency {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, length = 3)
    private String code;

    @Column(nullable = false)
    private String name;


    protected Currency() {}


    public Currency(String code, String name) {
        this.code = Objects.requireNonNull(code);
        this.name = Objects.requireNonNull(name);
    }


    public long getId() {
        return id;
    }


    public String getCode() {
        return code;
    }
    
    @Override
    public String toString() {
        return code;
    }
}
