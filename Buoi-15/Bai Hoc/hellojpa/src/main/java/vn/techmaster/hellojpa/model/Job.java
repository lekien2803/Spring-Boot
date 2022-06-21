package vn.techmaster.hellojpa.model;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Job {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
    private String title;
    private String description;
  }
