INSERT INTO task_rewards (REWARD_ID, REWARD_NAME) VALUES (2, '1 bitcoin');

INSERT INTO task_rewards (REWARD_ID, REWARD_NAME) VALUES (3, '1 sneakers bar');

INSERT INTO tasks (ID, NAME, TASKDESCRIPTION, TASKTIME) VALUES (1, 'pick up cat food from amazon', 'tikicat + instinct patte', 'next week');

INSERT INTO tasks (ID, NAME, TASKDESCRIPTION, TASKTIME) VALUES (4, 'basketball practice after school', 'drop of achiles at basketball practice', 'every other tuesday');

INSERT INTO TASKREWARDPREFERENCE  (TASK_ID, TASK_REWARD_ID, TASK_REWARD_POINTS) VALUES (1, 2, '222 reward points');

INSERT INTO TASKREWARDPREFERENCE  (TASK_ID, TASK_REWARD_ID, TASK_REWARD_POINTS) VALUES (4, 2, '333 reward points');

INSERT INTO users (USER_ID, EMAIL, USER_NAME, USER_TYPE) VALUES (5, 'lzhang422@gmail.com', 'dutchTender', 'admin');

INSERT INTO user_tasks (USER_ID, TASK_ID) VALUES (5, 1);

INSERT INTO user_tasks (USER_ID, TASK_ID) VALUES (5, 4);
