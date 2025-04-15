print("과제 1번")
epsilon = 0.0000001

def perceptron(x1, x2):
    w1, w2, b = 1.0, 1.0, -0.5
    sum = x1 * w1 + x2 * w2 + b
    if sum > epsilon:
        return 1
    else:
        return 0

print(perceptron(0, 0))
print(perceptron(1, 0))
print(perceptron(0, 1))
print(perceptron(1, 1))

print("\n과제 2 ~ 4번")
from sklearn.linear_model import Perceptron

X = [[0,0], [0,1], [1,0], [1,1]]
y = [0, 1, 1, 0]

clf = Perceptron(tol=1e-3, random_state=0)

clf.fit(X, y)

print(clf.predict(X))