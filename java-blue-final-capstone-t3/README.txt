Database Design:

BEGIN TRANSACTION;

CREATE TABLE games (game_id serial, game_name varchar(30) not null, status varchar(10), start_date timestamp not null, end_date timestamp not null, organizer_id int not null, constraint pk_game_id primary key (game_id), constraint fk_organizer_id foreign key (organizer_id) references users(user_id));

CREATE TABLE user_games (user_games_id serial, user_id int not null, game_id int not null, constraint pk_user_games_id primary key (user_games_id), constraint fk_user_id foreign key (user_id) references users(user_id) ON DELETE CASCADE ON UPDATE CASCADE, constraint fk_game_id foreign key (game_id) references games(game_id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE portfolio (portfolio_id serial, user_games_id int not null, cash_balance money, constraint pk_porfolio_id primary key (portfolio_id), constraint fk_user_games_id foreign key (user_games_id) references user_games(user_games_id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE portfolio_stock (portfolio_stock_id serial, portfolio_id int not null, stock_ticker varchar(5) not null, share_quantity int not null, constraint pk_portfolio_stock_id primary key (portfolio_stock_id), constraint fk_portfolio_id foreign key (portfolio_id) references portfolio(portfolio_id) ON DELETE CASCADE ON UPDATE CASCADE);

COMMIT TRANSACTION;