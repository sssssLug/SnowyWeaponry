package tk.sciwhiz12.snowyweaponry.entity;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.checkerframework.checker.nullness.qual.Nullable;
import tk.sciwhiz12.snowyweaponry.Reference;
import tk.sciwhiz12.snowyweaponry.item.CoredSnowballItem;

public class CoredSnowball extends Snowball {
    public CoredSnowball(EntityType<CoredSnowball> entityType, Level world) {
        super(entityType, world);
    }

    public CoredSnowball(Level world, double x, double y, double z) {
        super(Reference.EntityTypes.CORED_SNOWBALL, world);
        setPos(x, y, z);
    }

    public CoredSnowball(Level world, LivingEntity thrower) {
        this(world, thrower.getX(), thrower.getEyeY() - 0.1D, thrower.getZ());
        setOwner(thrower);
    }

    @Override
    protected Component getTypeName() {
        final ItemStack stack = this.getItem();
        if (!stack.isEmpty()) {
            return stack.getHoverName();
        }
        return super.getTypeName();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityTrace) {
        ItemStack stack = this.getItem();
        Entity entity = entityTrace.getEntity();

        int damage = 0;
        int looting = 0;
        if (Reference.Tags.FIRE_MOBS.contains(entity.getType())) {
            damage += 2; // Fire mobs damage modifier
        }

        if (stack.getItem() instanceof CoredSnowballItem item) {
            damage = item.getDamage();
            looting = item.getLootingLevel();
            @Nullable MobEffectInstance effect = item.getHitEffect();
            if (effect != null && entity instanceof LivingEntity) {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(effect));
            }
        }

        entity.hurt(Reference.DamageSources.causeSnowballDamage(this, this.getOwner(), looting), damage);
    }
}
