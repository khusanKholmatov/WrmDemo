CREATE TABLE IF NOT EXISTS public.warehouse
(
    id bigint NOT NULL DEFAULT nextval('warehouse_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    storing_price numeric(38,2),
    CONSTRAINT warehouse_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.warehouse
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.items
(
    id bigint NOT NULL DEFAULT nextval('items_id_seq'::regclass),
    final_price numeric(38,2),
    measure_type character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    quantity integer NOT NULL,
    warehouse_id bigint,
    CONSTRAINT items_pkey PRIMARY KEY (id),
    CONSTRAINT fkjr8lxh8gkj34fmt0evfh0o6xt FOREIGN KEY (warehouse_id)
    REFERENCES public.warehouse (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.items
    OWNER to postgres;

insert into warehouse (id, name, storing_price) values (1, 'W1', 1100000);
insert into warehouse (id, name, storing_price) values (2, 'W2', 1200000);
insert into warehouse (id, name, storing_price) values (3, 'W3', 1300000);