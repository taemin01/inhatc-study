과제 1번
    - 1 회귀  : 39.0
    - 2 이진 분류 : 0.2604635
    - 3 이진 분류 : 0.43375027
    - 4 다중 분류 : 1.2877443
    - 5 다중 분류 : 6.2146077

과제 2번
    - 스크린샷 첨부하겠습니다.

과제 3번
    - category_exe.ipynb, category_exe.py 파일 제출

과제 4번
    - 1
      - 코드 부분
        model.add(layers.Flatten(input_shape=(28, 28)))
        model.add(layers.Dense(32, activation='relu'))
        model.add(layers.Dense(10, activation='softmax'))
      - 정확도 : 0.8590999841690063
    - 2
      - 코드 부분
        model.add(layers.Flatten(input_shape=(28, 28)))
        model.add(layers.Dense(64, activation='relu'))
        model.add(layers.Dense(16, activation='relu'))
        model.add(layers.Dense(10, activation='softmax'))
      - 정확도 : 0.8701000213623047
    - 3 1050번째 이미지 예측 결과 : [0. 0. 0.06 0.01 0.82 0. 0.1 0. 0.01 0.]
    - 4 615번째 이미지 예측 결과 : [0. 0. 0. 0. 0. 0. 0. 1. 0. 0.]