--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-08-11 12:02:42

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
-- TOC entry 209 (class 1259 OID 41359)
-- Name: ingredients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingredients (
    ingredient character varying(30) NOT NULL,
    quantity integer
);


ALTER TABLE public.ingredients OWNER TO postgres;

--
-- TOC entry 3305 (class 0 OID 41359)
-- Dependencies: 209
-- Data for Name: ingredients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ingredients (ingredient, quantity) FROM stdin;
STEAMED_MILK	15
HONEY	10
CINNAMON	10
ESPRESSO	20
MILK_FOAM	15
BLACK_COFFEE	20
SYRUP	10
\.


--
-- TOC entry 3165 (class 2606 OID 41364)
-- Name: ingredients ingredients_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredients
    ADD CONSTRAINT ingredients_pk PRIMARY KEY (ingredient);


--
-- TOC entry 3163 (class 1259 OID 41362)
-- Name: ingredients_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX ingredients_name_uindex ON public.ingredients USING btree (ingredient);


-- Completed on 2022-08-11 12:02:42

--
-- PostgreSQL database dump complete
--

