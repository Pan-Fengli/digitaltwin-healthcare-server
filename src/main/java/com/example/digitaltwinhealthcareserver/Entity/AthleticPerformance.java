package com.example.digitaltwinhealthcareserver.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
 * 运动表现分析
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "athletic_performance")
public class AthleticPerformance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;

    //肌肉力量分析
    @Column(name = "muscle_general")//整体肌肉
    private String muscleGeneral;
    @Column(name = "muscle_upper")//上肢肌肉
    private String muscleUpper;
//    @Column(name = "muscle_lower")//下肢肌肉
//    private String muscleLower;
    @Column(name = "muscle_thigh")//大腿肌肉
    private String muscleThigh;
    @Column(name = "muscle_crus")//小腿肌肉
    private String muscleCrus;
    @Column(name = "muscle_back")//背部肌肉
    private String muscleBack;
    @Column(name = "muscle_chest")//胸部肌肉
    private String muscleChest;
    @Column(name = "muscle_waist")//腰腹肌肉
    private String muscleWaist;
    @Column(name = "muscle_hip")//臀部肌肉
    private String muscleHip;

    //体能分析
    //差，较差，中等，一般，良好，优秀
    @Column(name = "sensitivity")//灵敏性
    private String sensitivity;
    @Column(name = "balance")//平衡感
    private String balance;
    @Column(name = "coordination")//协调性
    private String coordination;
    @Column(name = "speed")//速度
    private String speed;
    @Column(name = "burst")//爆发力
    private String burst;
    @Column(name = "reaction")//反应时
    private String reaction;

    //
    @Column(name = "speed_change")//速度变化
    private String speedChange;//解析成一个list
    @Column(name = "acceleration_change")//加速度变化
    private String accelerationChange;//解析成一个list
    @Column(name = "range_leftshoulder")//左肩最大活动范围
    private Integer rangeLeftShoulder;
    @Column(name = "range_rightshoulder")//右肩最大活动范围
    private Integer rangeRightShoulder;
    @Column(name = "range_body")//躯干最大活动范围
    private Integer rangeBody;
    @Column(name = "range_leftleg")//左腿最大活动范围
    private Integer rangeLeftLeg;
    @Column(name = "range_rightleg")//右腿最大活动范围
    private Integer rangeRightLeg;

    @Column(name = "endurance_core")//核心耐力等级
    private String enduranceCore;
    @Column(name = "endurance_upper")//上肢耐力等级
    private String enduranceUpper;
    @Column(name = "endurance_lower")//下肢耐力等级
    private String enduranceLower;

    @Column(name = "gravity_forward")//重心向前变化
    private String gravityForward;//解析成一个list
    @Column(name = "gravity_rightside")//重心向右变化
    private String gravityRightside;//解析成一个list
    @Column(name = "stability_leftup")//左上肢稳定性变化
    private String stabilityLeftup;//解析成一个list
    @Column(name = "stability_rightup")//右上肢稳定性变化
    private String stabilityRightup;//解析成一个list
    @Column(name = "stability_leftdown")//左下肢稳定性变化
    private String stabilityLeftdown;//解析成一个list
    @Column(name = "stability_rightdown")//右下肢稳定性变化
    private String stabilityRightdown;//解析成一个list

    @Column(name = "force_left")//左脚冲击力
    private Double forceLeft;
    @Column(name = "force_right")//右脚冲击力
    private Double forceRight;
    @Column(name = "idle_time")//滞空时间(s)
    private Double idleTime;

    @Column(name = "reaction_simple")//简单反应时
    private String reactionSimple;
    @Column(name = "reaction_distinguish")//辨别反应时
    private String reactionDistinguish;
    @Column(name = "reaction_choose")//选择反应时
    private String reactionChoose;
    @Column(name = "reaction_min")//最快反应时间(s)
    private Double reactionMin;
    @Column(name = "reaction_max")//最慢反应时间(s)
    private Double reactionMax;
    @Column(name = "reaction_average")//平均反应时间(s)
    private Double reactionAverage;

    //关节损伤分析
    @Column(name = "knuckle_force_leftmax")//左边关节最大冲击指数（肩, 肘, 髋, 膝, 踝）
    private String knuckleForceLeftMax;//解析成一个list
    @Column(name = "knuckle_force_rightmax")//右边关节最大冲击指数（肩, 肘, 髋, 膝, 踝）
    private String knuckleForceRightMax;//解析成一个list
    @Column(name = "knuckle_force_leftaverage")//左边关节平均冲击指数（肩, 肘, 髋, 膝, 踝）
    private String knuckleForceLeftAverage;//解析成一个list
    @Column(name = "knuckle_force_rightaverage")//右边关节平均冲击指数（肩, 肘, 髋, 膝, 踝）
    private String knuckleForceRightAverage;//解析成一个list
    @Column(name = "knuckle_index_left")//左边关节损伤风险系数（肩, 肘, 髋, 膝, 踝）
    private String knuckleIndexLeft;//解析成一个list
    @Column(name = "knuckle_index_right")//右边关节损伤风险系数（肩, 肘, 髋, 膝, 踝）
    private String knuckleIndexRight;//解析成一个list

    //
    @Column(name = "calorie_total")//总卡路里消耗(cal)
    private Double calorieTotal;
    @Column(name = "calorie_rightleg")//右腿消耗(%)
    private String calorieRightLeg;
    @Column(name = "calorie_rightarm")//右臂消耗(%)
    private String calorieRightArm;
    @Column(name = "calorie_leftleg")//左腿消耗(%)
    private Double calorieLeftLeg;
    @Column(name = "calorie_leftarm")//左臂消耗(%)
    private Double calorieLeftArm;
    @Column(name = "calorie_chest")//胸部消耗(%)
    private Double calorieChest;
    @Column(name = "calorie_waist")//腰腹消耗(%)
    private Double calorieWaist;
}
