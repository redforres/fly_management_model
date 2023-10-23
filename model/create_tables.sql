CREATE TABLE "F_Applicant" (
  "id" SERIAL UNIQUE PRIMARY KEY,
  "first_name" varchar(20) NOT NULL,
  "middle_name" varchar(20),
  "last_name" varchar(20) NOT NULL,
  "email" varchar(50),
  "phone" varchar(20),
  "WeChat" varchar(20),
  "LinkedIn" varchar(20),
  "address_street" varchar(100),
  "address_suite_number" varchar(10),
  "address_city" varchar(50),
  "address_postal_code" varchar(20),
  "address_province" varchar(20),
  "address_country" varchar(20),
  "created_by" varchar(20),
  "modified_by" varchar(20),
  "created_at" date,
  "modified_at" date
);

CREATE TABLE "F_Applicant_Skill" (
  "applicant_id" serial,
  "skill_id" serial
);

CREATE TABLE "F_Skill" (
  "id" SERIAL UNIQUE PRIMARY KEY,
  "category" varchar(20),
  "name" varchar(20)
);

CREATE TABLE "F_Application" (
  "id" SERIAL UNIQUE PRIMARY KEY,
  "applicant_id" serial,
  "role" varchar(40),
  "status" varchar(20),
  "created_by" varchar(20),
  "modified_by" varchar(20),
  "created_at" date,
  "modified_at" date
);

CREATE TABLE "F_Document" (
  "id" SERIAL UNIQUE PRIMARY KEY,
  "application_id" serial,
  "content" bytea,
  "created_by" varchar(20),
  "modified_by" varchar(20),
  "created_at" date,
  "modified_at" date
);

CREATE TABLE "F_Role" (
  "id" SERIAL UNIQUE PRIMARY KEY,
  "name" varchar(255) UNIQUE NOT NULL
);

CREATE TABLE "F_User" (
  "id" SERIAL UNIQUE PRIMARY KEY,
  "username" varchar(255) UNIQUE NOT NULL,
  "password" varchar(255) NOT NULL,
  "role_id" serial NOT NULL,
  "active" boolean DEFAULT true
);

ALTER TABLE "F_Applicant_Skill" ADD FOREIGN KEY ("applicant_id") REFERENCES "F_Applicant" ("id");

ALTER TABLE "F_Applicant_Skill" ADD FOREIGN KEY ("skill_id") REFERENCES "F_Skill" ("id");

ALTER TABLE "F_Application" ADD FOREIGN KEY ("applicant_id") REFERENCES "F_Applicant" ("id");

ALTER TABLE "F_Document" ADD FOREIGN KEY ("application_id") REFERENCES "F_Application" ("id");

ALTER TABLE "F_User" ADD FOREIGN KEY ("role_id") REFERENCES "F_Role" ("id");
