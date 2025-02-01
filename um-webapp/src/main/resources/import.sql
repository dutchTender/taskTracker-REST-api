INSERT INTO task_rewards (REWARD_ID, REWARD_NAME) VALUES (1001, '1 bitcoin');

INSERT INTO task_rewards (REWARD_ID, REWARD_NAME) VALUES (999, '1 sneakers bar');

INSERT INTO tasks (ID, NAME, TASKDESCRIPTION, TASKTIME) VALUES (10001, 'pick up cat food from amazon', 'tikicat + instinct patte', 'next week');

INSERT INTO tasks (ID, NAME, TASKDESCRIPTION, TASKTIME) VALUES (10002, 'basketball practice after school', 'drop of achiles at basketball practice', 'every other tuesday');

INSERT INTO TASKREWARDPREFERENCE  (TASK_ID, TASK_REWARD_ID, TASK_REWARD_POINTS) VALUES (10001, 1001, '222 reward points');

INSERT INTO TASKREWARDPREFERENCE  (TASK_ID, TASK_REWARD_ID, TASK_REWARD_POINTS) VALUES (10002, 999, '333 reward points');

INSERT INTO users (USER_ID, EMAIL, USER_NAME, USER_TYPE) VALUES (1001, 'lzhang422@gmail.com', 'dutchTender', 'admin');

INSERT INTO user_tasks (USER_ID, TASK_ID) VALUES (1001, 10001);

INSERT INTO user_tasks (USER_ID, TASK_ID) VALUES (1001, 10002);
