Table users {
  id integer [primary key]
  created_at timestamp
  updated_at timestamp
  nama varchar
  div_id integer 
  dept_id integer 
  position_id integer
  level_id integer
  email varchar
  tel_no varchar
}

Table positions {
  id integer [primary key]
  name varchar
}

Table departements {
  id integer [primary key]
  name varchar
}

Table divisions {
  id integer [primary key]
  name varchar
}

Table levels {
  id integer [primary key]
  name varchar
}

Ref: users.position_id > positions.id
Ref: users.dept_id > departements.id
Ref: users.div_id > divisions.id
Ref: users.level_id > levels.id

Table evaluation_base {
  id integer [primary key]
  created_at timestamp
  updated_at timestamp
  start_dt timestamp
  end_dt timestamp
  addendum varchar
  to_hrd varchar [note: "Y N"]
}

Table evaluation_result_all {
  evaluation_id integer [primary key]
  id integer [primary key]
  factor integer 
  final_score_a double
  final_score_b integer
  final_weight double
  final_quality integer
  comment_to_evaluator varchar
}

Table plan_development {
  evaluation_id integer [primary key]
  id integer [primary key]
  goals varchar
  method varchar
  start_at timestamp
  end_at timestamp
  result varchar
}

Table last_criteria_evaluation {
  id integer [primary key]
  quality integer
  range_first double
  range_last double
  description varchar
}

Table evaluation_relation {
  evaluation_id integer [primary key]
  evaluated_id integer
  evaluator_id_1 integer
  evaluator_id_2 integer
  sign_plan_evaluated_at timestamp
  sign_plan_evaluator1_at timestamp
  sign_plan_evaluator2_at timestamp
  sign_result_evaluated_at timestamp
  sign_result_evaluator1_at timestamp
  sign_result_evaluator2_at timestamp
}

Table work_goals_base {
  evaluation_id integer [primary key]
  wgm_id integer [primary key]
  tot_param_a integer [note: "percentage"]
  final_result_work_goal integer
}

Table work_goals_map {
  id integer [primary key]
  no integer [primary key]
  created_at timestamp
  updated_at timestamp
  work_goals varchar
  target varchar
  realization varchar
  param_a integer [note: "percentage"]
  param_b integer
  param_c integer [note: "param a x param b"]
}

Table formal_coaching {
  evaluation_id integer [primary key]
  realization_dt timestamp
  target_user_evaluation_id integer
  evaluator_id_1 integer
  evaluator_id_2 integer
}

Ref: evaluation_base.id < work_goals_base.evaluation_id
Ref: work_goals_base.wgm_id < work_goals_map.id
Ref: evaluation_base.id < evaluation_relation.evaluation_id
Ref: evaluation_base.id < evaluation_result_all.evaluation_id
Ref: evaluation_base.id < plan_development.evaluation_id
Ref: evaluation_result_all.final_quality < last_criteria_evaluation.id
Ref: evaluation_base.id < formal_coaching.evaluation_id

Table work_scale {
  id integer [primary key]
  score integer
  category varchar
  est_ach_fst integer
  est_ach_last integer
  remark varchar
}

Table competency_scale {
  id integer [primary key]
  score integer
  remark varchar
}

Table type_competency {
  id integer [primary key]
  name varchar
}

Table competency_score_map {
  evaluation_id int [pk]
  id int [pk, increment]
  comp_map_id int [not null]
  num int [not null]
  created_at timestamp [not null, default: `current_timestamp()`]
  updated_at timestamp [not null, default: `current_timestamp()`]
  scale_id int
}

Table competency_map {
  id int [pk, increment]
  num int [not null]
  created_at timestamp [not null, default: `current_timestamp()`]
  updated_at timestamp [not null, default: `current_timestamp()`]
  type_comp int
  category varchar(255)
  description varchar(255)
  indicator_1 varchar(255)
  indicator_2 varchar(255)
  indicator_3 varchar(255)
  indicator_4 varchar(255)
  indicator_5 varchar(255)
}


Table competency_teknikal {
  evaluation_id int [pk]
  id int [pk]
  description varchar(500)
  scale_id int
}


Table competency_score_base {
  evaluation_id int [pk]
  comp_score_map_id int
  count_competencies int
  sum_score_competencies int
  final_result_competencies int
}

Ref: competency_score_map.evaluation_id < competency_score_base.evaluation_id
Ref: work_goals_map.param_b > work_scale.id
Ref: competency_teknikal.evaluation_id > competency_score_base.evaluation_id
Ref: competency_map.id > competency_score_map.comp_map_id
Ref: competency_map.type_comp > type_competency.id
Ref: evaluation_base.id < competency_score_base.evaluation_id
