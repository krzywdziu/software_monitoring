--
-- PostgreSQL database dump
--

-- Dumped from database version 14.8
-- Dumped by pg_dump version 14.8

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
-- Name: alerts; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE IF NOT EXISTS public.alerts (
    id bigint NOT NULL,
    box_ip character varying(255),
    description character varying(255),
    severity smallint,
    status smallint,
    "timestamp" timestamp(6) without time zone,
    user_id bigint
);


ALTER TABLE public.alerts OWNER TO admin;

--
-- Name: alerts_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE IF NOT EXISTS public.alerts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alerts_id_seq OWNER TO admin;

--
-- Name: alerts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.alerts_id_seq OWNED BY public.alerts.id;


--
-- Name: specializations; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE IF NOT EXISTS public.specializations (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.specializations OWNER TO admin;

--
-- Name: specializations_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE IF NOT EXISTS public.specializations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.specializations_id_seq OWNER TO admin;

--
-- Name: specializations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.specializations_id_seq OWNED BY public.specializations.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE IF NOT EXISTS public.users (
    id bigint NOT NULL,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    password_hash character varying(255) NOT NULL,
    phone_number character varying(255),
    role character varying(255)
);


ALTER TABLE public.users OWNER TO admin;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE IF NOT EXISTS public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO admin;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: users_specializations; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE IF NOT EXISTS public.users_specializations (
    user_id bigint NOT NULL,
    specialization_id bigint NOT NULL
);


ALTER TABLE public.users_specializations OWNER TO admin;

--
-- Name: alerts id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.alerts ALTER COLUMN id SET DEFAULT nextval('public.alerts_id_seq'::regclass);


--
-- Name: specializations id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.specializations ALTER COLUMN id SET DEFAULT nextval('public.specializations_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: alerts; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.alerts (id, box_ip, description, severity, status, "timestamp", user_id) FROM stdin;
1	10.0.8.137	Example description	0	1	2023-06-01 23:25:53.155452	5
2	10.0.8.137	Example description	1	1	2023-06-01 23:25:53.160169	2
3	10.0.8.137	Example description	2	1	2023-06-01 23:25:53.162154	10
4	10.0.8.137	Example description	3	1	2023-06-01 23:25:53.164007	2
5	10.0.8.137	Example description	4	1	2023-06-01 23:25:53.165842	7
6	10.0.8.137	Example description	5	1	2023-06-01 23:25:53.168058	5
7	10.0.8.137	Example description	6	1	2023-06-01 23:25:53.170196	10
8	10.0.8.137	Example description	7	1	2023-06-01 23:25:53.172159	3
9	10.0.8.137	Example description	0	1	2023-06-01 23:25:53.173954	4
10	10.0.8.137	Example description	1	1	2023-06-01 23:25:53.175668	5
11	10.0.8.137	Example description	2	1	2023-06-01 23:25:53.177469	11
12	10.0.8.137	Example description	3	1	2023-06-01 23:25:53.179194	8
13	10.0.8.137	Example description	4	1	2023-06-01 23:25:53.180864	5
14	10.0.8.137	Example description	5	1	2023-06-01 23:25:53.182534	5
15	10.0.8.137	Example description	6	1	2023-06-01 23:25:53.184101	4
16	10.0.8.137	Example description	7	1	2023-06-01 23:25:53.18542	8
17	10.0.8.137	Example description	0	1	2023-06-01 23:25:53.18665	1
18	10.0.8.137	Example description	1	1	2023-06-01 23:25:53.188087	6
19	10.0.8.137	Example description	2	1	2023-06-01 23:25:53.189348	10
20	10.0.8.137	Example description	3	1	2023-06-01 23:25:53.190542	8
21	10.0.8.137	Example description	4	1	2023-06-01 23:25:53.191936	2
22	10.0.8.137	Example description	5	1	2023-06-01 23:25:53.193449	11
\.


--
-- Data for Name: specializations; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.specializations (id, name) FROM stdin;
1	Docker
2	Linux
3	Cisco
4	SNMP
5	JAVA
6	C
7	GOLANG
8	PYTHON
9	TCP_IP
10	HTTP
11	SECURITY
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.users (id, email, first_name, last_name, password_hash, phone_number, role) FROM stdin;
1	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
2	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
3	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
4	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
5	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
6	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
7	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
8	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
9	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
10	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
11	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
12	example@domain.com	Robert	Makłowicz	{noop}pass	123666997	SERVICEMAN
13	admin@email.com	Admin	\N	{noop}pass	\N	ADMIN
14	test@mail	Test	\N	{noop}pass	\N	SERVICEMAN
\.


--
-- Data for Name: users_specializations; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.users_specializations (user_id, specialization_id) FROM stdin;
1	1
2	2
3	3
4	4
5	5
6	6
7	7
8	8
9	9
10	10
11	11
12	1
14	2
14	1
14	3
14	4
14	5
14	6
14	7
14	8
14	9
14	10
14	11
\.


--
-- Name: alerts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.alerts_id_seq', 22, true);


--
-- Name: specializations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.specializations_id_seq', 11, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.users_id_seq', 14, true);


--
-- Name: alerts alerts_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.alerts
    ADD CONSTRAINT alerts_pkey PRIMARY KEY (id);


--
-- Name: specializations specializations_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.specializations
    ADD CONSTRAINT specializations_pkey PRIMARY KEY (id);


--
-- Name: specializations uk_3lckmb5m8qfrxyyry2y6ovh9b; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.specializations
    ADD CONSTRAINT uk_3lckmb5m8qfrxyyry2y6ovh9b UNIQUE (name);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users_specializations users_specializations_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users_specializations
    ADD CONSTRAINT users_specializations_pkey PRIMARY KEY (user_id, specialization_id);


--
-- Name: idxensww7i9okbeu6g6ybusgdpi8; Type: INDEX; Schema: public; Owner: admin
--

CREATE INDEX idxensww7i9okbeu6g6ybusgdpi8 ON public.alerts USING btree (user_id);


--
-- Name: users_specializations fkfurle9m0mnlefyuoqjxno4n31; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users_specializations
    ADD CONSTRAINT fkfurle9m0mnlefyuoqjxno4n31 FOREIGN KEY (specialization_id) REFERENCES public.specializations(id);


--
-- Name: alerts fkqx4kjyy8qmc38cpa1pj5gp74i; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.alerts
    ADD CONSTRAINT fkqx4kjyy8qmc38cpa1pj5gp74i FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: users_specializations fkslrfqmcrfcfpdav471tjt2nwd; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.users_specializations
    ADD CONSTRAINT fkslrfqmcrfcfpdav471tjt2nwd FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

