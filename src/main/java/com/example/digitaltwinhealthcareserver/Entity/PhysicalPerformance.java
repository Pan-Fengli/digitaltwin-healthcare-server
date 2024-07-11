package com.example.digitaltwinhealthcareserver.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/*
* 体能8项
*/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "physical_performance")
public class PhysicalPerformance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "jump_distance")
    private Double jumpDistance;
    @Column(name = "jump_height")
    private Double jumpHeight;
    @Column(name = "jump_angle")
    private Double jumpAngle;
    @Column(name = "jump_grade")
    private String jumpGrade;

    @Column(name = "pushup_count")
    private Integer pushupCount;
    @Column(name = "pushup_speed")
    private Double pushupSpeed;
    @Column(name = "pushup_height")
    private Double pushupHeight;
    @Column(name = "pushup_grade")
    private String pushupGrade;

    @Column(name = "situp_count")
    private Integer situpCount;
    @Column(name = "situp_speed")
    private Double situpSpeed;
    @Column(name = "situp_angle")
    private Double situpAngle;
    @Column(name = "situp_grade")
    private String situpGrade;

    @Column(name = "stand_time")
    private Double standTime;
    @Column(name = "stand_stabletime")
    private Double standStableTime;
    @Column(name = "stand_angle")
    private Double standAngle;
    @Column(name = "stand_grade")
    private String standGrade;

    @Column(name = "run_count")
    private Integer runCount;
    @Column(name = "run_maxspeed")
    private Double runMaxSpeed;
    @Column(name = "run_averagespeed")
    private Double runAverageSpeed;
    @Column(name = "run_grade")
    private String runGrade;

    @Column(name = "bending_angle")
    private Double bendingAngle;
    @Column(name = "bending_leftangle")
    private Double bendingLeftAngle;
    @Column(name = "bending_rightangle")
    private Double bendingRightAngle;
    @Column(name = "bending_grade")
    private String bendingGrade;

    @Column(name = "highknee_count")
    private Integer highKneeCount;
    @Column(name = "highknee_leftangle")
    private Double highKneeLeftAngle;
    @Column(name = "highknee_rightangle")
    private Double highKneeRightAngle;
    @Column(name = "highknee_grade")
    private String highKneeGrade;

    //反应测试
    @Column(name = "reaction_time")
    private Double reactionTime;
    @Column(name = "reaction_mintime")
    private Double reactionMinTime;
    @Column(name = "reaction_rate")
    private String reactionRate;
    @Column(name = "reaction_hit")
    private Integer reactionHit;
    @Column(name = "reaction_combo")
    private Integer reactionCombo;
    @Column(name = "reaction_miss")
    private Integer reactionMiss;
    @Column(name = "reaction_error")
    private Integer reactionError;
    @Column(name = "reaction_grade")
    private String reactionGrade;

    //完成状态
    @Column(name = "test_time")
    private Double testTime;
    @Column(name = "test_complete")
    private Double testComplete;//完成度
    @Column(name = "test_beyond")
    private Double testBeyond;
    @Column(name = "test_grade")
    private String testGrade;
}
