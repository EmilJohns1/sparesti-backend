package edu.ntnu.idi.stud.team10.sparesti.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A savings goal is a goal that a user sets for themselves to save up for. It has a name, a target
 * amount, and a deadline.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "savings_goals")
public class SavingsGoal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "target_amount", nullable = false)
  private double targetAmount;

  @Column(name = "amount_saved", nullable = false)
  private double savedAmount;

  @Column(name = "deadline", nullable = false)
  private LocalDate deadline;

  @Column(name = "completed", nullable = false)
  private boolean completed;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
