package lambdasinaction.appa;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@Author(name = "Raoul")
@Author(name = "Mario")
@Author(name = "Alan")
@Author(name = "Alan")
public class Book {

  /**
   * 基本的流式处理
   */
  public static void main(String[] args) {
    Author[] authors = Book.class.getAnnotationsByType(Author.class);

    System.out.println("===遍历输出===");
    Arrays.stream(authors).forEach(author -> System.out.println(author.name()));

    System.out.println("===去重===");
    Arrays.stream(authors).distinct().forEach(author -> System.out.println(author.name()));

    System.out.println("===求取目标串长度===");
    Arrays.stream(authors)
        .distinct()
        .parallel()
        .filter(author -> author.name().equals("Alan"))
        .peek(author -> System.out.println(author.name()))
        .map(str -> str.name().concat(str.name()))
        .collect(Collectors.toList())
        .forEach(author -> System.out.println(author.length()));

    System.out.println("===peek等同于forEach，返回新的流继续处理===");
    Arrays.stream(authors)
        .parallel()
        .peek(author -> System.out.println(author.name()))
        .map(author -> author.name().length())
        .sorted(Comparator.comparing(author -> author))
        .max((a, b) -> {
          if (a > b) {
            return 1;
          }
          return 0;
        })
        .ifPresent(System.out::println);
    System.out.println("===parallel 与 sequential方式输出的结果有什么变化===");

    System.out.println("===使用Collectors分组===");
    Arrays.stream(authors)
        .parallel()
        .collect(Collectors.partitioningBy(author -> author.name().length() > 4))
        .forEach((a, b) -> {
          System.out.println("长度是否大于4:" + a);
          System.out.println("值:" + b);
        });
  }

}
