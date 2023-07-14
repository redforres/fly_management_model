from random import choices

applicants = [ i for i in range(1, 9) ]
skills = [ i for i in range(1, 11) ]
times = [ 1, 2, 3, 4, 5, 6, 7 ]
weights = [ 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.3 ]

# print(len(times), len(weights))

def gen():
    result = []
    for app in applicants:
        app_skills = choices(skills, k=choices(times, weights)[0])
        for skill in app_skills:
            print(f'({app}, {skill})')
            result.append((app, skill))
    return result

gen()
