package com.southsystem.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.southsystem.enums.TypeVote;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Schedule implements Serializable {
    private static final long serialVersionUID = 3700469750251131754L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private Integer yesVote;
    private Integer noVote;
    private TypeVote resul;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Associate> vote;
    @OneToOne(mappedBy = "schedule", fetch = FetchType.LAZY)
    private VoteSession voteSession;
}
