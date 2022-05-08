CREATE TABLE IF NOT EXISTS media_title
(
    media_title_id BIGINT NOT NULL AUTO_INCREMENT,
    imdb_id        VARCHAR(255),
    order_added    INTEGER,
    poster_url     VARCHAR(255),
    title_name     VARCHAR(255),
    media_type     VARCHAR(255),
    title_year     VARCHAR(255),
    PRIMARY KEY (media_title_id)
);