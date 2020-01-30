package com.forstudy.efjava.ch02.item02;

/**
* @author halfdev
* @since 2020-01-30
* 생성자에 매개변수가 많다면 빌더를 고려하라
 *
*/

/* Telescoping Constructor Pattern - 확장하기 어려움
    점층적 생성자 패턴을 사용한 클래스의 인스턴스를 생성하기 위해서는
    필요하지 않은 매개변수 값을 넘겨야 하는 경우도 있다.
    매개변수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다.
    개발자가 실수로 동일한 타입의 매개변수의 순서를 바꿔 넣어도
    컴파일러는 알아채지 못하고 런타임에 엉뚱한 동작을 할 가능성이 있으므로 주의해야 한다.
 */
public class NutritionFacts {

    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium,  0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    // Client Code
    public static void main(String[] args) {
        NutritionFacts justFood = new NutritionFacts(300,530,600,20, 70);
    }
}
