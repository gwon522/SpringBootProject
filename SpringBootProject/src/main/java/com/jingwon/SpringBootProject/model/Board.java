package com.jingwon.SpringBootProject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Board {
    @Id
    @Column(name = "no")
    @SequenceGenerator(name = "seq", sequenceName = "SEQ_BOARD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private long id;
    private String title;
    private String content;
    private String writer;
    private Date writedate;
    private long hit;
}
