--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3 (Debian 15.3-1.pgdg120+1)
-- Dumped by pg_dump version 15.3 (Debian 15.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: F_Applicant; Type: TABLE; Schema: public; Owner: heavywhale
--

CREATE TABLE public."F_Applicant" (
    id integer NOT NULL,
    first_name character varying(20) NOT NULL,
    middle_name character varying(20),
    last_name character varying(20) NOT NULL,
    email character varying(50),
    phone character varying(20),
    "WeChat" character varying(20),
    "LinkedIn" character varying(20),
    address_street character varying(100),
    address_suite_number character varying(10),
    address_city character varying(50),
    address_postal_code character varying(20),
    address_province character varying(20),
    address_country character varying(20),
    created_by character varying(20),
    modified_by character varying(20),
    created_at date,
    modified_at date
);


ALTER TABLE public."F_Applicant" OWNER TO heavywhale;

--
-- Name: F_Applicant_Skills; Type: TABLE; Schema: public; Owner: heavywhale
--

CREATE TABLE public."F_Applicant_Skills" (
    applicant_id integer NOT NULL,
    skill_id integer NOT NULL
);


ALTER TABLE public."F_Applicant_Skills" OWNER TO heavywhale;

--
-- Name: F_Applicant_Skills_applicant_id_seq; Type: SEQUENCE; Schema: public; Owner: heavywhale
--

CREATE SEQUENCE public."F_Applicant_Skills_applicant_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."F_Applicant_Skills_applicant_id_seq" OWNER TO heavywhale;

--
-- Name: F_Applicant_Skills_applicant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: heavywhale
--

ALTER SEQUENCE public."F_Applicant_Skills_applicant_id_seq" OWNED BY public."F_Applicant_Skills".applicant_id;


--
-- Name: F_Applicant_Skills_skill_id_seq; Type: SEQUENCE; Schema: public; Owner: heavywhale
--

CREATE SEQUENCE public."F_Applicant_Skills_skill_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."F_Applicant_Skills_skill_id_seq" OWNER TO heavywhale;

--
-- Name: F_Applicant_Skills_skill_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: heavywhale
--

ALTER SEQUENCE public."F_Applicant_Skills_skill_id_seq" OWNED BY public."F_Applicant_Skills".skill_id;


--
-- Name: F_Applicant_id_seq; Type: SEQUENCE; Schema: public; Owner: heavywhale
--

CREATE SEQUENCE public."F_Applicant_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."F_Applicant_id_seq" OWNER TO heavywhale;

--
-- Name: F_Applicant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: heavywhale
--

ALTER SEQUENCE public."F_Applicant_id_seq" OWNED BY public."F_Applicant".id;


--
-- Name: F_Application; Type: TABLE; Schema: public; Owner: heavywhale
--

CREATE TABLE public."F_Application" (
    id integer NOT NULL,
    applicant_id integer NOT NULL,
    role character varying(40),
    status character varying(20),
    created_by character varying(20),
    modified_by character varying(20),
    created_at date,
    modified_at date
);


ALTER TABLE public."F_Application" OWNER TO heavywhale;

--
-- Name: F_Application_applicant_id_seq; Type: SEQUENCE; Schema: public; Owner: heavywhale
--

CREATE SEQUENCE public."F_Application_applicant_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."F_Application_applicant_id_seq" OWNER TO heavywhale;

--
-- Name: F_Application_applicant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: heavywhale
--

ALTER SEQUENCE public."F_Application_applicant_id_seq" OWNED BY public."F_Application".applicant_id;


--
-- Name: F_Application_id_seq; Type: SEQUENCE; Schema: public; Owner: heavywhale
--

CREATE SEQUENCE public."F_Application_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."F_Application_id_seq" OWNER TO heavywhale;

--
-- Name: F_Application_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: heavywhale
--

ALTER SEQUENCE public."F_Application_id_seq" OWNED BY public."F_Application".id;


--
-- Name: F_Document; Type: TABLE; Schema: public; Owner: heavywhale
--

CREATE TABLE public."F_Document" (
    id integer NOT NULL,
    application_id integer NOT NULL,
    content bytea,
    created_by character varying(20),
    modified_by character varying(20),
    created_at date,
    modified_at date
);


ALTER TABLE public."F_Document" OWNER TO heavywhale;

--
-- Name: F_Document_application_id_seq; Type: SEQUENCE; Schema: public; Owner: heavywhale
--

CREATE SEQUENCE public."F_Document_application_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."F_Document_application_id_seq" OWNER TO heavywhale;

--
-- Name: F_Document_application_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: heavywhale
--

ALTER SEQUENCE public."F_Document_application_id_seq" OWNED BY public."F_Document".application_id;


--
-- Name: F_Document_id_seq; Type: SEQUENCE; Schema: public; Owner: heavywhale
--

CREATE SEQUENCE public."F_Document_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."F_Document_id_seq" OWNER TO heavywhale;

--
-- Name: F_Document_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: heavywhale
--

ALTER SEQUENCE public."F_Document_id_seq" OWNED BY public."F_Document".id;


--
-- Name: F_Skills; Type: TABLE; Schema: public; Owner: heavywhale
--

CREATE TABLE public."F_Skills" (
    id integer NOT NULL,
    category character varying(20),
    name character varying(20)
);


ALTER TABLE public."F_Skills" OWNER TO heavywhale;

--
-- Name: F_Skills_id_seq; Type: SEQUENCE; Schema: public; Owner: heavywhale
--

CREATE SEQUENCE public."F_Skills_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."F_Skills_id_seq" OWNER TO heavywhale;

--
-- Name: F_Skills_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: heavywhale
--

ALTER SEQUENCE public."F_Skills_id_seq" OWNED BY public."F_Skills".id;


--
-- Name: F_Applicant id; Type: DEFAULT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Applicant" ALTER COLUMN id SET DEFAULT nextval('public."F_Applicant_id_seq"'::regclass);


--
-- Name: F_Applicant_Skills applicant_id; Type: DEFAULT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Applicant_Skills" ALTER COLUMN applicant_id SET DEFAULT nextval('public."F_Applicant_Skills_applicant_id_seq"'::regclass);


--
-- Name: F_Applicant_Skills skill_id; Type: DEFAULT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Applicant_Skills" ALTER COLUMN skill_id SET DEFAULT nextval('public."F_Applicant_Skills_skill_id_seq"'::regclass);


--
-- Name: F_Application id; Type: DEFAULT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Application" ALTER COLUMN id SET DEFAULT nextval('public."F_Application_id_seq"'::regclass);


--
-- Name: F_Application applicant_id; Type: DEFAULT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Application" ALTER COLUMN applicant_id SET DEFAULT nextval('public."F_Application_applicant_id_seq"'::regclass);


--
-- Name: F_Document id; Type: DEFAULT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Document" ALTER COLUMN id SET DEFAULT nextval('public."F_Document_id_seq"'::regclass);


--
-- Name: F_Document application_id; Type: DEFAULT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Document" ALTER COLUMN application_id SET DEFAULT nextval('public."F_Document_application_id_seq"'::regclass);


--
-- Name: F_Skills id; Type: DEFAULT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Skills" ALTER COLUMN id SET DEFAULT nextval('public."F_Skills_id_seq"'::regclass);


--
-- Data for Name: F_Applicant; Type: TABLE DATA; Schema: public; Owner: heavywhale
--

COPY public."F_Applicant" (id, first_name, middle_name, last_name, email, phone, "WeChat", "LinkedIn", address_street, address_suite_number, address_city, address_postal_code, address_province, address_country, created_by, modified_by, created_at, modified_at) FROM stdin;
1	Beverly	R	Bennett	BeverlyRBennett@teleworm.us	626-839-9632	BevB_CA	BeverlyB_USA	656 Providence Lane	NA	La Puente	91744	CA	USA	Admin	Admin	2023-01-01	2023-01-02
2	Tara	W	Andrews	TaraWAndrews@jourrapide.com	704-656-5614	Tara_NC	Andrews_USA	888 Snyder Avenue	NA	Charlotte	28202	NC	USA	Admin	Admin	2023-01-03	2023-01-04
3	Иван		Зайцев	izabella87@list.ru	8-800-748-4128	Зайцев_wechat	Зайцев_linkedin	12 спуск Ломоносова	NA	город Серпухов	240206	ULY	Russia	Admin	Admin	2023-01-05	2023-01-06
4	Henry	K	Lawson	HenryKLawson@rhyta.com	615-989-2170	Lawson_TN	HenryK_USA	3685 Cottonwood Lane	NA	Nashville	37203	TN	USA	Admin	Admin	2023-01-07	2023-01-08
5	Xiu Juan		Tan	XiuJuanTan@jourrapide.com	306-987-4761	XiuJuan_Sweden	Tan_Sweden	Bursiljum 57	NA	LÖVÅNGER	93010		Sweden	Admin	Admin	2023-02-01	2023-02-02
6	沖原		智子	Whoem1997@armyspy.com	0913-8732592	Whoem1997_Japan	Whoem1997_JPN	廣川町宇野	002	坂本市	2045800	滋贺县	Japan	Admin	Admin	2023-02-03	2023-02-04
7	Ranger		Deserres	RangerDeserres@rhyta.com	66 66 10	Ranger_Greenland	Deserres_GL	Gl. Sygehusvej 81	008	Narsarsuaq	3923	NA	Greenland	Admin	Admin	2023-02-05	2023-02-06
8	Flora		Gavrilova	FloraGavrilova@armyspy.com	05.22.23.45.25	Flora_France	Gavrilova_FR	89, Faubourg Saint Honoré	NA	PAU	64000	NA	France	Admin	Admin	2023-02-07	2023-02-08
\.


--
-- Data for Name: F_Applicant_Skills; Type: TABLE DATA; Schema: public; Owner: heavywhale
--

COPY public."F_Applicant_Skills" (applicant_id, skill_id) FROM stdin;
1	3
1	5
1	10
2	6
2	4
2	7
2	1
3	2
3	5
3	7
3	10
3	4
3	9
4	9
4	2
4	1
4	7
5	6
5	3
5	1
5	10
5	4
5	2
5	7
6	1
6	4
7	2
7	7
7	5
7	10
7	3
7	9
8	3
8	10
8	5
8	4
8	7
8	9
8	1
\.


--
-- Data for Name: F_Application; Type: TABLE DATA; Schema: public; Owner: heavywhale
--

COPY public."F_Application" (id, applicant_id, role, status, created_by, modified_by, created_at, modified_at) FROM stdin;
1	1	Web Developer	Applied	Admin	Admin	2023-01-01	2023-01-02
2	2	Software Engineer	Interviewed	Admin	Admin	2023-01-03	2023-01-04
3	3	Data Analyst	Hired	Admin	Admin	2023-01-05	2023-01-06
4	4	Cloud Architect	Rejected	Admin	Admin	2023-01-07	2023-01-08
5	5	Front-end Developer	Applied	Admin	Admin	2023-02-01	2023-02-02
6	5	Back-end Developer	Interviewed	Admin	Admin	2023-02-03	2023-02-04
7	6	Database Manager	Hired	Admin	Admin	2023-02-05	2023-02-06
8	6	Data Scientist	Applied	Admin	Admin	2023-02-07	2023-02-08
9	7	Software Tester	Rejected	Admin	Admin	2023-02-09	2023-02-10
10	7	DevOps Engineer	Hired	Admin	Admin	2023-02-11	2023-02-12
11	7	UI/UX Designer	Interviewed	Admin	Admin	2023-02-13	2023-02-14
12	8	Full-stack Developer	Applied	Admin	Admin	2023-02-15	2023-02-16
13	8	Web Designer	Hired	Admin	Admin	2023-02-17	2023-02-18
14	8	Project Manager	Interviewed	Admin	Admin	2023-02-19	2023-02-20
\.


--
-- Data for Name: F_Document; Type: TABLE DATA; Schema: public; Owner: heavywhale
--

COPY public."F_Document" (id, application_id, content, created_by, modified_by, created_at, modified_at) FROM stdin;
1	1	\\x00	Beverly Bennett	Admin	2023-01-01	2023-01-02
2	2	\\x00	Tara Andrews	Admin	2023-01-03	2023-01-04
3	3	\\x00	Gerald Driggers	Admin	2023-01-05	2023-01-06
4	3	\\x00	Gerald Driggers	Admin	2023-01-07	2023-01-08
5	4	\\x00	Henry Lawson	Admin	2023-01-09	2023-01-10
6	4	\\x00	Henry Lawson	Admin	2023-01-11	2023-01-12
7	5	\\x00	Xiu Juan Tan	Admin	2023-02-01	2023-02-02
8	5	\\x00	Xiu Juan Tan	Admin	2023-02-03	2023-02-04
9	5	\\x00	Xiu Juan Tan	Admin	2023-02-05	2023-02-06
10	6	\\x00	沖原 智子	Admin	2023-02-07	2023-02-08
11	6	\\x00	沖原 智子	Admin	2023-02-09	2023-02-10
12	6	\\x00	沖原 智子	Admin	2023-02-11	2023-02-12
13	7	\\x00	Ranger Deserres	Admin	2023-02-13	2023-02-14
14	7	\\x00	Ranger Deserres	Admin	2023-02-15	2023-02-16
15	7	\\x00	Ranger Deserres	Admin	2023-02-17	2023-02-18
16	7	\\x00	Ranger Deserres	Admin	2023-02-19	2023-02-20
17	8	\\x00	Flora Gavrilova	Admin	2023-02-21	2023-02-22
18	8	\\x00	Flora Gavrilova	Admin	2023-02-23	2023-02-24
19	8	\\x00	Flora Gavrilova	Admin	2023-02-25	2023-02-26
20	8	\\x00	Flora Gavrilova	Admin	2023-02-27	2023-02-28
\.


--
-- Data for Name: F_Skills; Type: TABLE DATA; Schema: public; Owner: heavywhale
--

COPY public."F_Skills" (id, category, name) FROM stdin;
1	Programming	Python
2	Programming	JavaScript
3	Programming	Java
4	Web Development	React.js
5	Web Development	Angular.js
6	Web Development	HTML/CSS
7	Database Management	SQL
8	Database Management	NoSQL
9	Cloud Computing	AWS
10	Cloud Computing	Azure
\.


--
-- Name: F_Applicant_Skills_applicant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: heavywhale
--

SELECT pg_catalog.setval('public."F_Applicant_Skills_applicant_id_seq"', 1, false);


--
-- Name: F_Applicant_Skills_skill_id_seq; Type: SEQUENCE SET; Schema: public; Owner: heavywhale
--

SELECT pg_catalog.setval('public."F_Applicant_Skills_skill_id_seq"', 1, false);


--
-- Name: F_Applicant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: heavywhale
--

SELECT pg_catalog.setval('public."F_Applicant_id_seq"', 8, true);


--
-- Name: F_Application_applicant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: heavywhale
--

SELECT pg_catalog.setval('public."F_Application_applicant_id_seq"', 1, false);


--
-- Name: F_Application_id_seq; Type: SEQUENCE SET; Schema: public; Owner: heavywhale
--

SELECT pg_catalog.setval('public."F_Application_id_seq"', 14, true);


--
-- Name: F_Document_application_id_seq; Type: SEQUENCE SET; Schema: public; Owner: heavywhale
--

SELECT pg_catalog.setval('public."F_Document_application_id_seq"', 1, false);


--
-- Name: F_Document_id_seq; Type: SEQUENCE SET; Schema: public; Owner: heavywhale
--

SELECT pg_catalog.setval('public."F_Document_id_seq"', 20, true);


--
-- Name: F_Skills_id_seq; Type: SEQUENCE SET; Schema: public; Owner: heavywhale
--

SELECT pg_catalog.setval('public."F_Skills_id_seq"', 10, true);


--
-- Name: F_Applicant F_Applicant_pkey; Type: CONSTRAINT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Applicant"
    ADD CONSTRAINT "F_Applicant_pkey" PRIMARY KEY (id);


--
-- Name: F_Application F_Application_pkey; Type: CONSTRAINT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Application"
    ADD CONSTRAINT "F_Application_pkey" PRIMARY KEY (id);


--
-- Name: F_Document F_Document_pkey; Type: CONSTRAINT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Document"
    ADD CONSTRAINT "F_Document_pkey" PRIMARY KEY (id);


--
-- Name: F_Skills F_Skills_pkey; Type: CONSTRAINT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Skills"
    ADD CONSTRAINT "F_Skills_pkey" PRIMARY KEY (id);


--
-- Name: F_Applicant_Skills F_Applicant_Skills_applicant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Applicant_Skills"
    ADD CONSTRAINT "F_Applicant_Skills_applicant_id_fkey" FOREIGN KEY (applicant_id) REFERENCES public."F_Applicant"(id);


--
-- Name: F_Applicant_Skills F_Applicant_Skills_skill_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Applicant_Skills"
    ADD CONSTRAINT "F_Applicant_Skills_skill_id_fkey" FOREIGN KEY (skill_id) REFERENCES public."F_Skills"(id);


--
-- Name: F_Application F_Application_applicant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Application"
    ADD CONSTRAINT "F_Application_applicant_id_fkey" FOREIGN KEY (applicant_id) REFERENCES public."F_Applicant"(id);


--
-- Name: F_Document F_Document_application_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: heavywhale
--

ALTER TABLE ONLY public."F_Document"
    ADD CONSTRAINT "F_Document_application_id_fkey" FOREIGN KEY (application_id) REFERENCES public."F_Application"(id);


--
-- PostgreSQL database dump complete
--

