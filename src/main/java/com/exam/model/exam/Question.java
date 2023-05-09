package com.exam.model.exam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quesId;

    @Column(length = 5000)
    private String content;

    private String image;

    @Column(length = 2000)
    private String option1;
    @Column(length = 2000)
    private String option2;
    @Column(length = 2000)
    private String option3;
    @Column(length = 2000)
    private String option4;
    @Column(length = 2000)
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}
