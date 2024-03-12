package com.example.projectfrontend2_2.courseReg;


    public class CourseRegDTO {
        private Long class_id;
        private Long stud_id;

        public CourseRegDTO(Long class_id, Long stud_id) {
            this.class_id = class_id;
            this.stud_id = stud_id;
        }

        public Long getClass_id() {
            return class_id;
        }

        public void setClass_id(Long class_id) {
            this.class_id = class_id;
        }

        public Long getStud_id() {
            return stud_id;
        }

        public void setStud_id(Long stud_id) {
            this.stud_id = stud_id;
        }


    }
