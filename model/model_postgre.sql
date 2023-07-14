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

CREATE TABLE "F_Applicant_Skills" (
  "applicant_id" serial,
  "skill_id" serial,
  "name" varchar(20)
);

CREATE TABLE "F_Skills" (
  "id" SERIAL UNIQUE PRIMARY KEY,
  "category" varchar(20)
);

CREATE TABLE "F_Application" (
  "id" SERIAL UNIQUE PRIMARY KEY,
  "applicant_id" serial,
  "role" varchar(20),
  "status" varchar(10),
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

ALTER TABLE "F_Applicant_Skills" ADD FOREIGN KEY ("applicant_id") REFERENCES "F_Applicant" ("id");

ALTER TABLE "F_Applicant_Skills" ADD FOREIGN KEY ("skill_id") REFERENCES "F_Skills" ("id");

ALTER TABLE "F_Application" ADD FOREIGN KEY ("applicant_id") REFERENCES "F_Applicant" ("id");

ALTER TABLE "F_Document" ADD FOREIGN KEY ("application_id") REFERENCES "F_Application" ("id");
