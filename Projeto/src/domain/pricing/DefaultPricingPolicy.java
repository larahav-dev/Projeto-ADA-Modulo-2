package domain.pricing;

import java.math.BigDecimal;

// OCP: novas políticas podem ser adicionadas sem tocar no domínio/serviço
public class DefaultPricingPolicy implements PricingPolicy {
    @Override
    public BigDecimal apply(BigDecimal requestedUnitPrice) {
        if (requestedUnitPrice == null || requestedUnitPrice.signum() <= 0)
            throw new IllegalArgumentException("Preço unitário inválido");
        return requestedUnitPrice;
    }
}
