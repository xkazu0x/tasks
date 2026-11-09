# get all task lists
# GET http://localhost:8080/task-lists

# create task list
# POST http://localhost:8080/task-lists
# {
#   "id": null,
#   "title": "my first task list!",
#   "description": "this should have a lot of tasks!",
#   "count": 0,
#   "progress": null,
#   "tasks": null
# }

# get task list by id
# GET http://localhost:8080/task-lists/927086d2-a666-4b77-b0f8-b7ae089968d0

# update task list
# PUT http://localhost:8080/task-lists/927086d2-a666-4b77-b0f8-b7ae089968d0
# {
#   "id": "927086d2-a666-4b77-b0f8-b7ae089968d0",
#   "title": "my first UPDATED task list!",
#   "description": "this should have a lot of tasks! but there is NONE!",
#   "count": 0,
#   "progress": null,
#   "tasks": null
# }

# delete task list
# DELETE http://localhost:8080/task-lists/927086d2-a666-4b77-b0f8-b7ae089968d0

# get all tasks from task list
# GET http://localhost:8080/task-lists/ab447ac1-04d8-449b-ba90-df79e01c4967/tasks

# create task
# POST http://localhost:8080/task-lists/ab447ac1-04d8-449b-ba90-df79e01c4967/tasks
# {
#   "id": null,
#   "title": "CLEAN MY ROOM!",
#   "description": "CLEAN MY DIRTY ASS ROOM!",
#   "due": null,
#   "status": null,
#   "priority": "HIGH"
# }

# get task
# GET http://localhost:8080/task-lists/ab447ac1-04d8-449b-ba90-df79e01c4967/tasks/4a2e62e0-acef-4a86-9e32-a959db372f65

# update task
# PUT http://localhost:8080/task-lists/ab447ac1-04d8-449b-ba90-df79e01c4967/tasks/4a2e62e0-acef-4a86-9e32-a959db372f65
# {
#   "id": "4a2e62e0-acef-4a86-9e32-a959db372f65",
#   "title": "CLEAN MY ROOM! PART 2",
#   "description": "CLEAN MY DIRTY ASS ROOM! BUT IT'S NOT THAT DEEP",
#   "due": null,
#   "status": "OPEN",
#   "priority": "LOW"
# }

# delete task
# DELETE http://localhost:8080/task-lists/ab447ac1-04d8-449b-ba90-df79e01c4967/tasks/4a2e62e0-acef-4a86-9e32-a959db372f65
