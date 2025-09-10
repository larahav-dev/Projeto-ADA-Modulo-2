package domain.pricing;

import java.math.BigDecimal;

public interface PricingPolicy {
    BigDecimal apply(BigDecimal requestedUnitPrice);
}
