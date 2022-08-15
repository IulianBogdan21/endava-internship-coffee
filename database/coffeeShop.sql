--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-08-11 18:05:37

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
-- TOC entry 211 (class 1259 OID 41528)
-- Name: coffee_recipe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coffee_recipe (
    coffee_name character varying(255) NOT NULL,
    recipe integer,
    recipe_key character varying(255) NOT NULL
);


ALTER TABLE public.coffee_recipe OWNER TO postgres;

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
-- TOC entry 210 (class 1259 OID 41365)
-- Name: recipes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recipes (
    name character varying(30) NOT NULL,
    ingredient character varying(30),
    amount integer,
    dtype character varying(31) NOT NULL
);


ALTER TABLE public.recipes OWNER TO postgres;

--
-- TOC entry 3321 (class 0 OID 41528)
-- Dependencies: 211
-- Data for Name: coffee_recipe; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.coffee_recipe (coffee_name, recipe, recipe_key) FROM stdin;
Espresso	1	ESPRESSO
Black coffee	1	BLACK_COFFEE
Cappucino	1	ESPRESSO
Cappucino	1	STEAMED_MILK
Cappucino	2	MILK_FOAM
Coffee Miel	1	BLACK_COFFEE
Coffee Miel	1	HONEY
Coffee Miel	1	CINNAMON
Coffee Miel	1	STEAMED_MILK
CoffeeLatte	1	ESPRESSO
CoffeeLatte	2	STEAMED_MILK
CoffeeLatte	1	MILK_FOAM
Machiatto	1	ESPRESSO
Machiatto	1	MILK_FOAM
\.


--
-- TOC entry 3319 (class 0 OID 41359)
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
-- TOC entry 3320 (class 0 OID 41365)
-- Dependencies: 210
-- Data for Name: recipes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.recipes (name, ingredient, amount, dtype) FROM stdin;
Espresso	\N	\N	Espresso
Black coffee	\N	\N	BlackCoffee
Cappucino	\N	\N	Cappucino
Coffee Miel	\N	\N	CoffeeMiel
CoffeeLatte	\N	\N	CoffeeLatte
Machiatto	\N	\N	Machiatto
\.


--
-- TOC entry 3178 (class 2606 OID 41534)
-- Name: coffee_recipe coffee_recipe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee_recipe
    ADD CONSTRAINT coffee_recipe_pkey PRIMARY KEY (coffee_name, recipe_key);


--
-- TOC entry 3173 (class 2606 OID 41364)
-- Name: ingredients ingredients_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredients
    ADD CONSTRAINT ingredients_pk PRIMARY KEY (ingredient);


--
-- TOC entry 3176 (class 2606 OID 41370)
-- Name: recipes recipes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipes
    ADD CONSTRAINT recipes_pk PRIMARY KEY (name);


--
-- TOC entry 3171 (class 1259 OID 41362)
-- Name: ingredients_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX ingredients_name_uindex ON public.ingredients USING btree (ingredient);


--
-- TOC entry 3174 (class 1259 OID 41368)
-- Name: recipes_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX recipes_name_uindex ON public.recipes USING btree (name);


--
-- TOC entry 3179 (class 2606 OID 41535)
-- Name: coffee_recipe fkocllr2xmna3u63v8ynudlkp3h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee_recipe
    ADD CONSTRAINT fkocllr2xmna3u63v8ynudlkp3h FOREIGN KEY (coffee_name) REFERENCES public.recipes(name);


-- Completed on 2022-08-11 18:05:37

--
-- PostgreSQL database dump complete
--

