package com.demo.coffeeshop.model.entity;

import com.demo.coffeeshop.model.entity.enums.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class ProductTest {

    @PersistenceContext
    EntityManager em;

    private final String[] productNames = {
            "잠비아 블론드 로스트", "베란다 블렌드", "디카페인 하우스 블렌드", "에티오피아", "콜롬비아",
            "파이크 플레이스™ 로스트", "하우스 블렌드", "케냐", "별다방 블렌드", "오텀 블렌드",
            "애니버서리 블렌드", "에스프레소 로스트", "수마트라", "카페 베로나", "탄자니아 몬듈 에스테이트",
            "선 드라이드 브라질 파젠다 산타 클라라", "콜롬비아 핀카 라스 준타스 핑크 버번", "발리 마운트 바투르",
            "콜롬비아 나리뇨 그라노스 데 에스페란자", "인도네시아 구눙 레이서"
    };

    private final String[] descriptions = {
            "블론드 로스트 특유의 부드럽고 화사한 산미와 열대과일의 상큼하고 달콤한 복합적인 풍미를 느낄 수 있는 특별한 매력의 잠비아 블론드 로스트를 지금 만나보세요",
            "부드럽고 친근한 성향을 가진 은은한 블렌드입니다.",
            "깔끔하고 균형 잡힌 맛이 특징인 중간 정도 무게감의 커피",
            "다크초콜릿의 벨벳 같은 부드러움과 후추 같은 스파이시함, 달콤한 감귤류의 풍미가 특징인 원두",
            "견과류의 풍미를 간직한 부드럽고 균형 잡힌 커피",
            "부드러운 뒷맛이 특징이며 은은한 코코아와 구운 견과류 향이 특징인 커피",
            "깔끔하고 균형 잡힌 맛이 특징인 중간 정도 무게감의 커피",
            "열대성 과일의 향기와 신비로움으로 깔끔한 주스와 같은 느낌을 가진 커피",
            "사랑하지 않을 수 없는, 한국만을 위한 특별한 커피!오직 스타벅스 커피 코리아에서만 만날 수 있는 특별한 원두를 소개합니다.",
            "3가지 지역의 원두가 블렌딩되어 달콤하고 묵직하면서도 은은하고 산뜻한 허브의 스파이시한 매력을 가득 담은 오텀 블렌드로 가을을 더욱 풍성하게 즐겨보세요!",
            "숙성된 수마트라의 강렬하면서도 우아한 삼나무의 아로마와 묵직하면서도 알싸한 허브의 풍미를 가진 애니버서리 블렌드로 잊지 못할 한 잔의 커피를 경험해 보세요!",
            "풍부하고 조화로운 느낌으로 스타벅스 에스프레소 음료의 기본이 되는 커피",
            "강렬하면서도 가득 찬 풍미, 흙 내음을 전해주는 무게감 있는 커피",
            "따뜻한 로맨스를 상상하게 해 주는 깊고 달콤한 맛의 커피",
            "감귤과 모렐로 체리의 풍미",
            "브라질너트와 레드 커런트의 풍미",
            "리치와 복숭아의 풍미",
            "향긋하고 풋풋한 레몬 잎의 뉘앙스,달콤한 마지팬의 풍미와 코코아 파우더의 쌉쌀한 피니시",
            "구운 피스타치오의 고소한 아로마와 달콤한 솔티드 카라멜의 피니쉬",
            "알싸한 바나나 고추의 풍미와 부드럽고 쌉싸름한 코코아 버터의 아로마"
    };

    private final String[] imageUrls = {
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/08/[11151545]_20240822111534689.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/06/[11019855]_20240603132537705.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2023/11/[11059722]_20231110094425022.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2023/05/[11029237]_20230526150757666.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/06/[11019857]_20240603132700727.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/06/[11017010]_20240603132415175.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2023/07/[11017015]_20230731135420432.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2023/05/[11019859]_20230526150647251.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2021/07/[11125845]_20210721100203916.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/08/[11005686]_20240822111843509.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/08/[250034]_20240822112051970.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2023/07/[11017024]_20230713084036753.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2023/05/[11019856]_20230526150402905.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2023/05/[11017011]_20230526150104314.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/08/[11160369]_20240808102058124.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/08/[11160368]_20240808101951857.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/08/[11160367]_20240808101828637.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/05/[11146733]_20240510150718263.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/04/[11154317]_20240409102204566.jpg",
            "https://image.istarbucks.co.kr/upload/store/skuimg/2024/04/[11154313]_20240409102037100.jpg"
    };

    @Test
    public void productInit() {
        for (int i = 0; i < productNames.length; i++) {
            String productName = getCutProductName(productNames[i]);
            Products product = new Products(
                    productName,
                    ProductCategory.COFFEE.name(),
                    10000L + (i * 500),
                    descriptions[i],
                    imageUrls[i]

            );
            em.persist(product);
        }
        em.flush();
        em.clear();

        long count = (long) em.createQuery("SELECT COUNT(p) FROM products p").getSingleResult();
        assertEquals(26, count);

        // UUID 생성 확인
        List<Products> products = em.createQuery("SELECT p FROM products p", Products.class)
                .getResultList();
        for (Products product : products) {
            assertNotNull(product.getProductId());
            assertTrue(product.getProductId() instanceof UUID);
            assertTrue(product.getProductName().length() <= 20);
        }
    }

    private String getCutProductName(String productName) {
        if (productName.length() > 20) {
            return productName.substring(0, 20);
        }
        return productName;
    }
}
