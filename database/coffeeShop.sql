--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

-- Started on 2022-08-12 12:51:15

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
-- TOC entry 214 (class 1259 OID 41558)
-- Name: orderdto_coffeesfromorder; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderdto_coffeesfromorder (
    orderdto_order_id integer NOT NULL,
    amount integer,
    coffeename character varying(255),
    price double precision
);


ALTER TABLE public.orderdto_coffeesfromorder OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 41541)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    order_id integer NOT NULL,
    customer_name character varying(30),
    order_type character varying(15),
    total_cost double precision,
    order_date timestamp without time zone
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 41540)
-- Name: orders_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_order_id_seq OWNER TO postgres;

--
-- TOC entry 3344 (class 0 OID 0)
-- Dependencies: 212
-- Name: orders_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_order_id_seq OWNED BY public.orders.order_id;


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
-- TOC entry 3180 (class 2604 OID 41544)
-- Name: orders order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN order_id SET DEFAULT nextval('public.orders_order_id_seq'::regclass);


--
-- TOC entry 3335 (class 0 OID 41528)
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
-- TOC entry 3333 (class 0 OID 41359)
-- Dependencies: 209
-- Data for Name: ingredients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ingredients (ingredient, quantity) FROM stdin;
STEAMED_MILK	15
MILK_FOAM	15
SYRUP	10
BLACK_COFFEE	20
HONEY	10
ESPRESSO	20
CINNAMON	10
\.


--
-- TOC entry 3338 (class 0 OID 41558)
-- Dependencies: 214
-- Data for Name: orderdto_coffeesfromorder; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orderdto_coffeesfromorder (orderdto_order_id, amount, coffeename, price) FROM stdin;
1	2	Espresso	2.5
1	2	Machiatto	6
2	2	Espresso	2.5
2	2	Hari	10.1
3	2	Espresso	2.5
\.


--
-- TOC entry 3337 (class 0 OID 41541)
-- Dependencies: 213
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (order_id, customer_name, order_type, total_cost, order_date) FROM stdin;
1	Mircea	PICKUP	17	2022-08-12 11:53:51.109686
2	Hari	DELIVERY	25.2	2022-08-12 12:32:29.497758
3	Horia	PICKUP	5	2022-08-12 12:45:35.839153
\.


--
-- TOC entry 3334 (class 0 OID 41365)
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
-- TOC entry 3345 (class 0 OID 0)
-- Dependencies: 212
-- Name: orders_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_order_id_seq', 1, false);


--
-- TOC entry 3188 (class 2606 OID 41534)
-- Name: coffee_recipe coffee_recipe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee_recipe
    ADD CONSTRAINT coffee_recipe_pkey PRIMARY KEY (coffee_name, recipe_key);


--
-- TOC entry 3183 (class 2606 OID 41364)
-- Name: ingredients ingredients_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredients
    ADD CONSTRAINT ingredients_pk PRIMARY KEY (ingredient);


--
-- TOC entry 3191 (class 2606 OID 41547)
-- Name: orders orders_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pk PRIMARY KEY (order_id);


--
-- TOC entry 3186 (class 2606 OID 41370)
-- Name: recipes recipes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipes
    ADD CONSTRAINT recipes_pk PRIMARY KEY (name);


--
-- TOC entry 3181 (class 1259 OID 41362)
-- Name: ingredients_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX ingredients_name_uindex ON public.ingredients USING btree (ingredient);


--
-- TOC entry 3189 (class 1259 OID 41545)
-- Name: orders_order_id_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX orders_order_id_uindex ON public.orders USING btree (order_id);


--
-- TOC entry 3184 (class 1259 OID 41368)
-- Name: recipes_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX recipes_name_uindex ON public.recipes USING btree (name);


--
-- TOC entry 3193 (class 2606 OID 41561)
-- Name: orderdto_coffeesfromorder fkjguqabco5e7c58sgfs0abrl70; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdto_coffeesfromorder
    ADD CONSTRAINT fkjguqabco5e7c58sgfs0abrl70 FOREIGN KEY (orderdto_order_id) REFERENCES public.orders(order_id);


--
-- TOC entry 3192 (class 2606 OID 41535)
-- Name: coffee_recipe fkocllr2xmna3u63v8ynudlkp3h; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee_recipe
    ADD CONSTRAINT fkocllr2xmna3u63v8ynudlkp3h FOREIGN KEY (coffee_name) REFERENCES public.recipes(name);


-- Completed on 2022-08-12 12:51:16

--
-- PostgreSQL database dump complete
--

