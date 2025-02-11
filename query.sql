-- Table: public.usuario

-- DROP TABLE IF EXISTS public.usuario;

CREATE TABLE IF NOT EXISTS public.usuario
(
    id integer NOT NULL DEFAULT nextval('usuario_id_seq'::regclass),
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(14) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT usuario_pkey PRIMARY KEY (id),
    CONSTRAINT usuario_cpf_unique UNIQUE (cpf),
    CONSTRAINT usuario_email_unique UNIQUE (email)
    )

-- Table: public.livro

-- DROP TABLE IF EXISTS public.livro;

CREATE TABLE IF NOT EXISTS public.livro
(
    isbn character varying(20) COLLATE pg_catalog."default" NOT NULL,
    autor character varying(100) COLLATE pg_catalog."default" NOT NULL,
    titulo character varying(100) COLLATE pg_catalog."default" NOT NULL,
    editora character varying(100) COLLATE pg_catalog."default" NOT NULL,
    ano integer NOT NULL,
    emprestado boolean NOT NULL DEFAULT false,
    CONSTRAINT livro_pkey PRIMARY KEY (isbn),
    CONSTRAINT chk_ano CHECK (ano > 0)
    )

-- Table: public.emprestimo

-- DROP TABLE IF EXISTS public.emprestimo;

CREATE TABLE IF NOT EXISTS public.emprestimo
(
    id integer NOT NULL DEFAULT nextval('emprestimo_id_seq'::regclass),
    data_emprestimo date NOT NULL,
    data_devolucao date NOT NULL,
    usuario_id integer NOT NULL,
    livro_isbn character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT emprestimo_pkey PRIMARY KEY (id),
    CONSTRAINT emprestimo_livro_isbn_fkey FOREIGN KEY (livro_isbn)
    REFERENCES public.livro (isbn) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT emprestimo_usuario_id_fkey FOREIGN KEY (usuario_id)
    REFERENCES public.usuario (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT chk_datadevolucao CHECK (datadevolucao >= dataemprestimo)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.emprestimo
    OWNER to usuario;