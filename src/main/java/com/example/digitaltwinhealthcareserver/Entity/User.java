package com.example.digitaltwinhealthcareserver.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
// @Accessors 注解详解 https://blog.csdn.net/sunnyzyq/article/details/119992746
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private Integer height;
    private Integer weight;
    private String gender;
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)//
    @JoinColumn(name = "user_id")
    @NotFound(action = NotFoundAction.IGNORE) // 找不到就变成null
    private List<PhysicalPerformance> physicalPerformanceList = new ArrayList<>();
}
