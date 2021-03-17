package com.jingwon.SpringBootProject.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Board {
    @Id
    @Column(name = "no")
    @SequenceGenerator(name = "seq", sequenceName = "SEQ_BOARD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private long id;
    @Size(min = 2, max = 50)
    @NotNull
    private String title;
    @NotNull
    private String content;
    private String writer;
    private Date writedate;
    private long hit;
}
