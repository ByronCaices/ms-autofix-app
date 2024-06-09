--
-- PostgreSQL database dump
--

-- Dumped from database version 12.19
-- Dumped by pg_dump version 12.19

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

DROP DATABASE IF EXISTS "prices-db";
--
-- Name: prices-db; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "prices-db" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE "prices-db" OWNER TO postgres;

\connect -reuse-previous=on "dbname='prices-db'"

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
-- Name: prices; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prices (
    id bigint NOT NULL,
    engine character varying(255) NOT NULL,
    price integer NOT NULL,
    repair_type integer NOT NULL
);


ALTER TABLE public.prices OWNER TO postgres;

--
-- Name: prices_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prices_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prices_id_seq OWNER TO postgres;

--
-- Name: prices_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prices_id_seq OWNED BY public.prices.id;


--
-- Name: prices id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prices ALTER COLUMN id SET DEFAULT nextval('public.prices_id_seq'::regclass);


--
-- Data for Name: prices; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prices (id, engine, price, repair_type) FROM stdin;
1	gas	120	1
2	gas	130	2
3	gas	350	3
4	gas	210	4
5	gas	150	5
6	gas	100	6
7	gas	100	7
8	gas	180	8
9	gas	150	9
10	gas	130	10
11	gas	80	11
12	diesel	120	1
13	diesel	130	2
14	diesel	450	3
15	diesel	210	4
16	diesel	150	5
17	diesel	120	6
18	diesel	100	7
19	diesel	180	8
20	diesel	150	9
21	diesel	140	10
22	diesel	80	11
23	hybrid	180	1
24	hybrid	190	2
25	hybrid	700	3
26	hybrid	300	4
27	hybrid	200	5
28	hybrid	450	6
29	hybrid	100	7
30	hybrid	210	8
31	hybrid	180	9
32	hybrid	220	10
33	hybrid	80	11
34	electric	220	1
35	electric	230	2
36	electric	800	3
37	electric	300	4
38	electric	250	5
39	electric	0	6
40	electric	100	7
41	electric	250	8
42	electric	180	9
43	electric	0	10
44	electric	80	11
\.


--
-- Name: prices_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prices_id_seq', 125, true);


--
-- Name: prices prices_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prices
    ADD CONSTRAINT prices_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

