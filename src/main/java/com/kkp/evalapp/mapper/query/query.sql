-- Tabel users
CREATE TABLE users (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  nama VARCHAR(255),
  div_id INTEGER,
  dept_id INTEGER,
  position_id INTEGER,
  level_id INTEGER,
  email VARCHAR(255),
  tel_no VARCHAR(255),
  FOREIGN KEY (position_id) REFERENCES positions(id),
  FOREIGN KEY (dept_id) REFERENCES departements(id),
  FOREIGN KEY (div_id) REFERENCES divisions(id),
  FOREIGN KEY (level_id) REFERENCES levels(id)
);

CREATE TABLE positions (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255)
);

CREATE TABLE departements (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255)
);

CREATE TABLE divisions (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255)
);

CREATE TABLE levels (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255)
);

-- Tabel evaluation_base
CREATE TABLE evaluation_base (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  start_dt varchar(12),
  end_dt varchar(12),
  addendum VARCHAR(1000),
  to_hrd VARCHAR(1)
)auto_increment = 1;

-- Tabel evaluation_result_all
CREATE TABLE evaluation_result_all (
  evaluation_id INTEGER,
  id INTEGER ,
  factor INTEGER,
  final_score_a DOUBLE,
  final_score_b INTEGER,
  final_weight DOUBLE,
  final_quality INTEGER,
  comment_to_evaluator VARCHAR(1000),
  PRIMARY KEY (evaluation_id, id),
  FOREIGN KEY (evaluation_id) REFERENCES evaluation_base(id)
);


-- Tabel plan_development
CREATE TABLE plan_development (
  evaluation_id INTEGER,
  id INTEGER AUTO_INCREMENT,
  goals VARCHAR(1000),
  method VARCHAR(255),
  start_at TIMESTAMP,
  end_at TIMESTAMP,
  result VARCHAR(255),
  PRIMARY KEY (evaluation_id, id),
  FOREIGN KEY (evaluation_id) REFERENCES evaluation_base(id)
);

-- Tabel last_criteria_evaluation
CREATE TABLE last_criteria_evaluation (
  id INTEGER PRIMARY KEY,
  quality INTEGER,
  range_first DOUBLE,
  range_last DOUBLE,
  description VARCHAR(1000)
);

-- Tabel evaluation_relation
CREATE TABLE evaluation_relation (
  evaluation_id INTEGER,
  evaluated_id INTEGER,
  evaluator_id_1 INTEGER,
  evaluator_id_2 INTEGER,
  sign_plan_evaluated_at Date,
  sign_plan_evaluator1_at Date,
  sign_plan_evaluator2_at Date,
  sign_result_evaluated_at Date,
  sign_result_evaluator1_at Date,
  sign_result_evaluator2_at Date,
  PRIMARY KEY (evaluation_id),
  FOREIGN KEY (evaluation_id) REFERENCES evaluation_base(id),
  FOREIGN KEY (evaluated_id) REFERENCES users(id),
  FOREIGN KEY (evaluator_id_1) REFERENCES users(id),
  FOREIGN KEY (evaluator_id_2) REFERENCES users(id)
);


-- Tabel work_goals_base
CREATE TABLE work_goals_base (
  evaluation_id INTEGER,
  wgm_id INTEGER,
  tot_param_a INTEGER,
  final_result_work_goal INTEGER,
  PRIMARY KEY (evaluation_id, wgm_id),
  FOREIGN KEY (evaluation_id) REFERENCES evaluation_base(id),
  FOREIGN KEY (wgm_id) REFERENCES work_goals_map(id)
);

-- Tabel work_goals_map
CREATE TABLE work_goals_map (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  no INTEGER,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  work_goals VARCHAR(1000),
  target VARCHAR(255),
  realization VARCHAR(255),
  param_a INTEGER,
  param_b INTEGER,
  param_c INTEGER,
  FOREIGN KEY (param_b) REFERENCES work_scale(id)
);

-- Tabel formal_coaching
CREATE TABLE formal_coaching (
  evaluation_id INTEGER,
  realization_dt TIMESTAMP,
  target_user_evaluation_id INTEGER,
  evaluator_id_1 INTEGER,
  evaluator_id_2 INTEGER,
  PRIMARY KEY (evaluation_id),
  FOREIGN KEY (evaluation_id) REFERENCES evaluation_base(id)
);

CREATE TABLE competency_score_map (
  id INT AUTO_INCREMENT,
  comp_map_id INT,
  num INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  scale_id INT,
  PRIMARY KEY (id, comp_map_id, num),
  FOREIGN KEY (comp_map_id) REFERENCES competency_map(id),
  FOREIGN KEY (scale_id) REFERENCES competency_scale(id)
)auto_increment=1;

-- Tabel competency_map
CREATE TABLE competency_map (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  no INTEGER,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  type INTEGER,
  category VARCHAR(255),
  description VARCHAR(1000),
  indicator_1 VARCHAR(255),
  indicator_2 VARCHAR(255),
  indicator_3 VARCHAR(255),
  indicator_4 VARCHAR(255),
  indicator_5 VARCHAR(255),
  score INTEGER,
  FOREIGN KEY (score) REFERENCES competency_scale(id),
  FOREIGN KEY (type) REFERENCES type_competency(id)
);

-- Tabel competency_score_base
CREATE TABLE competency_score_base (
  evaluation_id INTEGER,
  comp_map_id INTEGER,
  count_competencies INTEGER,
  sum_score_competencies INTEGER,
  final_result_competencies INTEGER,
  PRIMARY KEY (evaluation_id),
  FOREIGN KEY (evaluation_id) REFERENCES evaluation_base(id),
  FOREIGN KEY (comp_map_id) REFERENCES competency_map(id)
);

CREATE TABLE `map_departements_positions` (
  `dept_id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  PRIMARY KEY (`dept_id`,`position_id`),
  KEY `fk_dept_id` (`dept_id`),
  KEY `fk_position_id` (`position_id`),
  CONSTRAINT `fk_map_dept_post_dept` FOREIGN KEY (`dept_id`) REFERENCES `departements` (`id`),
  CONSTRAINT `fk_map_dept_post_position` FOREIGN KEY (`position_id`) REFERENCES `positions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- Indeks untuk tabel users
ALTER TABLE users ADD INDEX idx_users_nama (nama);
ALTER TABLE users ADD INDEX idx_users_email (email);

-- Indeks untuk tabel positions
ALTER TABLE positions ADD INDEX idx_positions_name (name);

-- Indeks untuk tabel departements
ALTER TABLE departements ADD INDEX idx_departements_name (name);

-- Indeks untuk tabel divisions
ALTER TABLE divisions ADD INDEX idx_divisions_name (name);

-- Indeks untuk tabel levels
ALTER TABLE levels ADD INDEX idx_levels_name (name);

-- Indeks untuk tabel evaluation_base
ALTER TABLE evaluation_base ADD INDEX idx_evaluation_base_start_dt (start_dt);
ALTER TABLE evaluation_base ADD INDEX idx_evaluation_base_end_dt (end_dt);

-- Indeks untuk tabel evaluation_result_all
ALTER TABLE evaluation_result_all ADD INDEX idx_evaluation_result_all_evaluation_id (evaluation_id);
ALTER TABLE evaluation_result_all ADD INDEX idx_evaluation_result_all_final_quality (final_quality);

-- Indeks untuk tabel plan_development
ALTER TABLE plan_development ADD INDEX idx_plan_development_evaluation_id (evaluation_id);

-- Indeks untuk tabel last_criteria_evaluation
ALTER TABLE last_criteria_evaluation ADD INDEX idx_last_criteria_evaluation_quality (quality);

-- Indeks untuk tabel evaluation_relation
ALTER TABLE evaluation_relation ADD INDEX idx_evaluation_relation_evaluation_id (evaluation_id);

-- Indeks untuk tabel work_goals_base
ALTER TABLE work_goals_base ADD INDEX idx_work_goals_base_evaluation_id (evaluation_id);

-- Indeks untuk tabel work_goals_map
ALTER TABLE work_goals_map ADD INDEX idx_work_goals_map_no (no);

-- Indeks untuk tabel formal_coaching
ALTER TABLE formal_coaching ADD INDEX idx_formal_coaching_evaluation_id (evaluation_id);

-- Indeks untuk tabel work_scale
ALTER TABLE work_scale ADD INDEX idx_work_scale_category (category);

-- Indeks untuk tabel competency_scale
ALTER TABLE competency_scale ADD INDEX idx_competency_scale_score (score);

-- Indeks untuk tabel competency_map
ALTER TABLE competency_map ADD INDEX idx_competency_map_category (category);

-- Indeks untuk tabel type_competency
ALTER TABLE type_competency ADD INDEX idx_type_competency_name (name);

-- Indeks untuk tabel competency_score_base
ALTER TABLE competency_score_base ADD INDEX idx_competency_score_base_evaluation_id (evaluation_id);




---

CREATE TABLE competency_scale (
  id INT AUTO_INCREMENT PRIMARY KEY,
  score INT,
  remark VARCHAR(255)
)auto_increment = 1;

CREATE TABLE competency_map (
  id INT AUTO_INCREMENT,
  num INT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  type_comp INT,
  category VARCHAR(255),
  description VARCHAR(255),
  indicator_1 VARCHAR(255),
  indicator_2 VARCHAR(255),
  indicator_3 VARCHAR(255),
  indicator_4 VARCHAR(255),
  indicator_5 VARCHAR(255),
  scale_id INT,
  PRIMARY KEY (id, num)
);

select * from competency_map

ALTER TABLE competency_map
  ADD FOREIGN KEY (type_comp) REFERENCES type_competency(id);

CREATE TABLE type_competency (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
)auto_increment = 1;

CREATE TABLE competency_score_base (
  evaluation_id INT PRIMARY KEY,
  comp_map_id INT,
  count_competencies INT,
  sum_score_competencies INT,
  final_result_competencies INT,
  FOREIGN KEY (comp_map_id) REFERENCES competency_map(id)
);

ALTER TABLE competency_map
  ADD FOREIGN KEY (type) REFERENCES type_competency(id);
